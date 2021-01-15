package tech.torah.aldis.androidapp.dataClassesAndInterfaces

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.ShiurFullPage
import tech.torah.aldis.androidapp.dialogs.ShiurimSortOrFilterDialog
import java.util.*
private const val TAG = "FunctionLibrary"
/**
 * This is a library for functions used throughout the app, such a filter() for a RecyclerView, to facilitate DRYness.
 * */
object FunctionLibrary {
    /**
     * Filters a recycler view based on a constraint
     * @param constraint can be a partial phrase from a SearchView or an entry from a [ChooserFastScrollerDialog]
     * @param originalList the list which is displayed when no filter constraints are imposed on the dataset
     * @param workingList the list being displayed in the recyclerview*/
    fun <T, VH : RecyclerView.ViewHolder> filter(
        constraint: String,
        originalList: List<T>,
        workingList: MutableList<T>,
        recyclerView: RecyclerView.Adapter<VH>,
        tabType: TabType = TabType.NONE,
        exactMatch: Boolean = false,
        filterWithinPreviousResults: Boolean = false,
        animation: Boolean = false
    ) {
        //TODO Would it make filtering more efficient by using indices instead of full objects? Or are they just references...?
        fun String.matchesConstraint(constraint: String) = when(exactMatch){
            true -> this == constraint
            false -> this.contains(constraint)
        }
        if (animation) {
            if (filterWithinPreviousResults) TODO("animation version of fiter does not currently take into account whether they are searching within previous results (i.e. it ignores it and only filters within the original list), and therefore does not supprt filtering within previous results.")
            var completeListIndex = 0
            var filteredListIndex = 0
            //TODO does not account for wmpty constraint
            while (completeListIndex < originalList.size) {
                val element: T = originalList[completeListIndex]
                val filter: T = workingList[filteredListIndex]
                val elementReceiver = element.getReceiver(tabType)
                val filterReceiver = filter.getReceiver(tabType)
                if (elementReceiver.toLowerCase(Locale.ROOT).trim().matchesConstraint(constraint)) {
                    if (filteredListIndex < workingList.size) {
                        if (elementReceiver != filterReceiver) {
                            workingList.add(filteredListIndex, element)
                            recyclerView.notifyItemInserted(filteredListIndex)
                        }
                    } else {
                        workingList.add(filteredListIndex, element)
                        recyclerView.notifyItemInserted(filteredListIndex)
                    }
                    filteredListIndex++
                } else if (filteredListIndex < workingList.size) {
                    if (elementReceiver == filterReceiver) {
                        workingList.removeAt(filteredListIndex)
                        recyclerView.notifyItemRemoved(filteredListIndex)
                    }
                }
                completeListIndex++
            }
        } else {
/* // */if (!filterWithinPreviousResults) workingList.clear()
            if (constraint.isEmpty()) { //TODO I feel like i should add "&& workingList.size > 0" but I feel like when i first wrote this code i worked through it better than i have it now. I hope I remember to test it by filtering letter by letter, and then selecting the whole searchphrase and deleting it at one time. I am assuming that this TODO is only applicable for
                if(workingList.size != 0) workingList.clear()
                workingList.addAll(originalList)
            }
            else {
                val filterPattern = constraint.toLowerCase(Locale.ROOT).trim()
                val tempWorkingList: List<T> =
                    if (filterWithinPreviousResults) workingList.toList() else listOf()
                val listToFilterWithin =
                    if (filterWithinPreviousResults) tempWorkingList else originalList
                for (element in listToFilterWithin) {
                    val filterCondition = element.getReceiver(tabType).toLowerCase(Locale.ROOT)
                        .matchesConstraint(filterPattern)
                    val filterConditionMet =
                        if (filterWithinPreviousResults) filterCondition.not() else filterCondition
                    if (filterConditionMet) {
                        if (filterWithinPreviousResults) workingList.remove(element)
                        else workingList.add(element)
                    }
                }
            }
            recyclerView.notifyDataSetChanged()
        }
        Log.d(TAG,"Working List (After mutation) = $workingList")
    }

    fun <T, VH : RecyclerView.ViewHolder> reset(originalList: List<T>,
              workingList: MutableList<T>,
              recyclerView: RecyclerView.Adapter<VH>){
        //TODO make reset more efficient by using indices?
        workingList.clear()
        workingList.addAll(originalList)
        recyclerView.notifyDataSetChanged()
    }

    private fun <T> T.getReceiver(
        tabType: TabType
    ): String = when (this) {
        //TODO What about when the user is filtering for only playlists with e.g. 5 or more shiurim? What about searching for a category or series? add support all search criteria
        is Speaker -> name
        is ShiurFullPage -> when (tabType) {
            TabType.CATEGORY -> category!!
            TabType.SERIES -> series!!
            TabType.SPEAKER -> speaker!!
            TabType.NONE -> title!!
        }
        is Playlist -> playlistName
        else -> this as String
    }
    fun setupFilterAndSearch(
        menu: Menu?,
        menuInflater: MenuInflater,
        torahFilterableCallback: TorahFilterable,
        fragmentManager: FragmentManager,
        TAG: String,
        listOfSpeakerNames: List<String>,
        listOfCategoryNames: List<String>,
        listOfSeriesNames: List<String>
    ) {
//        menu as Menu // let the compiler know that i want to treat menu:Menu? as Menu

        if (menu != null) {
            setupFilterButton(
                menuInflater,
                menu,
                torahFilterableCallback,
                listOfSpeakerNames,
                listOfCategoryNames,
                listOfSeriesNames,
                fragmentManager,
                TAG
            )
            setupSearchView(menu, torahFilterableCallback)
        }
    }

    fun setupFilterButton(
        menuInflater: MenuInflater,
        menu: Menu,
        torahFilterableCallback: TorahFilterable,
        listOfSpeakerNames: List<String>,
        listOfCategoryNames: List<String>,
        listOfSeriesNames: List<String>,
        fragmentManager: FragmentManager,
        TAG: String
    ) {
        menuInflater.inflate(R.menu.downloads_favorites_history_pages_menu, menu)
        val filterItem: MenuItem = menu.findItem(R.id.filter_button)
        filterItem.setOnMenuItemClickListener {
            ShiurimSortOrFilterDialog(
                torahFilterableCallback,
                listOfSpeakerNames.toList(),
                listOfCategoryNames.toList(),
                listOfSeriesNames.toList()
            )
                // I figure that it is worth the cost of passing new objects to the sort dialog to avoid the cost of
                // eventual bugs due to passing in a reference to a mutable list
                .show(fragmentManager, TAG)
            true
        }
    }

    fun setupSearchView(
        menu: Menu,
        torahFilterableCallback: TorahFilterable
    ) {
        val searchView = menu.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                torahFilterableCallback.search(newText ?: "")
                return false
            }
        })
    }
}