package tech.torah.aldis.androidapp.dataClassesAndInterfaces

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
import com.google.android.material.shape.MaterialShapeDrawable
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.activities.BaseShiurimPageActivity
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.ShiurFullPage
import tech.torah.aldis.androidapp.dialogs.ShiurimSortOrFilterDialog
import tech.torah.aldis.androidapp.mEntireApplicationContext
import java.util.*


/**
 * This is a library for functions used throughout the app, such a filter() for a RecyclerView, to facilitate DRYness.
 * */
object FunctionLibrary {
    private const val TAG = "FunctionLibrary"

    /**
     * When the user clicks the cancel button on the [SearchView], by default the first time they
     * click it, the [SeachView]'s [EditText] is cleared but the [SearchView] remains opened. Only
     * once they click it again does the [SearchView] close. If this is true, the [SearchView] is
     * closed and the query is set to "" - thereby updating the list.
     * */
    private const val userWantsToCancelSearchBarOnFirstCancelButtonClick = true

    /**
     * Filters a recycler view based on a constraint
     * @param constraint can be a partial phrase from a SearchView or an entry from a [ChooserFastScrollerDialog].
     * Will be ignored when using a [it.sephiroth.android.library.rangeseekbar.RangeSeekBar] to
     * filter by length.
     * @param originalList the list which is displayed when no filter constraints are imposed on the dataset
     * @param workingList the list being displayed in the recyclerview
     * @param exactMatch  false if filtering/searching from SearchView and results should be partial.
     * true if filtering from dialog
     * */
    fun <T, VH : RecyclerView.ViewHolder> filter(
        constraint: String,
        originalList: List<T>,
        workingList: MutableList<T>,
        recyclerView: RecyclerView.Adapter<VH>,
        shiurFilterOption: ShiurFilterOption = ShiurFilterOption.NONE,
        exactMatch: Boolean = false,
        filterWithinPreviousResults: Boolean = false,
        animation: Boolean = false,
        intRange: IntRange? = null
    ) {
        //TODO Would it make filtering more efficient by using indices instead of full objects? Or are they just references...?
        /*fun Int.matchesConstraint(constraint:Int):Boolean = when(equality){
            Equality.GREATER_THAN -> this > constraint
            Equality.GREATER_THAN_OR_EQUAL -> this >= constraint
            Equality.EQUAL -> this == constraint
            Equality.LESS_THAN_OR_EQUAL -> this <= constraint
            Equality.LESS_THAN -> this < constraint
        }*/
        fun String.matchesConstraint(constraint: String) = when (exactMatch) {
            true -> {//request is coming from filter dialog; will only ever be inexact match when searching from SearchView, which should only search through name and possibly description
                if (this.isDigitsOnly() && intRange != null) this.toInt() in intRange //if it is a number, theoretically the only thing it could be is a length of a shiur
                else this == constraint
            }
            false -> {
                this.contains(constraint)
            }
        }

        if (animation) {
            //not sure how this works, just copying it from the popular SO question about filtering with SearchView
            if (filterWithinPreviousResults) TODO("animation version of fiter does not currently take into account whether they are searching within previous results (i.e. it ignores it and only filters within the original list), and therefore does not supprt filtering within previous results.")
            var completeListIndex = 0
            var filteredListIndex = 0
            //TODO does not account for wmpty constraint
            while (completeListIndex < originalList.size) {
                val element: T = originalList[completeListIndex]
                val filter: T = workingList[filteredListIndex]
                val elementReceiver = element.getReceiver(shiurFilterOption)
                val filterReceiver = filter.getReceiver(shiurFilterOption)//no clue what this does
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
                if (workingList.size != 0) workingList.clear()
                workingList.addAll(originalList)
            } else {
                val filterPattern = constraint.toLowerCase(Locale.ROOT).trim()
                val tempWorkingList: List<T> =
                    if (filterWithinPreviousResults) workingList.toList() else listOf()
                val listToFilterWithin =
                    if (filterWithinPreviousResults) tempWorkingList else originalList
                for (element in listToFilterWithin) {
                    val filterCondition =
                        element.getReceiver(shiurFilterOption).toLowerCase(Locale.ROOT)
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
        Log.d(TAG, "Working List (After mutation) = $workingList")
    }

    fun <T, VH : RecyclerView.ViewHolder> reset(
        originalList: List<T>,
        workingList: MutableList<T>,
        recyclerView: RecyclerView.Adapter<VH>
    ) {
        //TODO make reset more efficient by using indices?
        workingList.clear()
        workingList.addAll(originalList)
        recyclerView.notifyDataSetChanged()
    }

    private fun <T> T.getReceiver(
        shiurFilterOption: ShiurFilterOption
    ): String = when (this) {
        //TODO What about when the user is filtering for only playlists with e.g. 5 or more shiurim? What about searching for a category or series? add support all search criteria
        is Speaker -> name
        is ShiurFullPage -> when (shiurFilterOption) {
            ShiurFilterOption.CATEGORY -> category!!
            ShiurFilterOption.SERIES -> series!!
            ShiurFilterOption.SPEAKER -> speaker!!
            ShiurFilterOption.NONE -> title!!
            ShiurFilterOption.HAS_DESCRIPTION -> if (description!!.isBlank()) "no" /*doesn't have a description*/ else "yes" /*does have a description*/ //used yes/no because the user presented options in the dropdown menu for these are yes and no
            ShiurFilterOption.HAS_ATTACHMENT -> if (attachment!!.isBlank()) "no" /*doesn't have an attachment link*/ else "yes" /*does have an attachment link*/ //^^^
            ShiurFilterOption.LENGTH -> length!!
            ShiurFilterOption.DATE_ADDED_TO_PERSONAL_COLLECTION -> TODO("Not yet sure how to implement this")
            ShiurFilterOption.DATE_UPLOADED -> uploaded!! //TODO should probably implement this using date picker
            ShiurFilterOption.LANGUAGE -> language!!
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
        mapOfFilterCriteriaData: Map<ShiurFilterOption, List<String>>,
        callingFromActivity: Boolean,
        callingActivity: Activity?,
        contextForClosingKeyboard: Context?,
        viewForClosingKeyboard: View?
    ) {
//        menu as Menu // let the compiler know that i want to treat menu:Menu? as Menu
        if (menu != null) {
            setupFilterButton(
                menuInflater,
                menu,
                torahFilterableCallback,
                mapOfFilterCriteriaData,
                fragmentManager,
                TAG,
                alsoUsingSearchButton = true,
                shouldInflateLayout = true
            )
            setupSearchView(
                menuInflater, menu, torahFilterableCallback,
                alsoUsingFilterButton = true,
                shouldInflateLayout = false,
                callingFromActivity,
                callingActivity,
                contextForClosingKeyboard,
                viewForClosingKeyboard
            )
        }
    }

    // Practically, this function is never called without also calling [setupSearchView], but it can
    // be if need be.
    fun setupFilterButton(
        menuInflater: MenuInflater,
        menu: Menu,
        torahFilterableCallback: TorahFilterable,
        mapOfFilterCriteriaData: Map<ShiurFilterOption, List<String>>,
        fragmentManager: FragmentManager,
        TAG: String,
        alsoUsingSearchButton: Boolean,
        shouldInflateLayout: Boolean
    ) {
        if (shouldInflateLayout) menuInflater.inflate(
            if (alsoUsingSearchButton) R.menu.search_bar_and_filter_button else R.menu.filter_button_only,
            menu
        )
        val filterItem: MenuItem? = menu.findItem(R.id.filter_button)
        filterItem?.setOnMenuItemClickListener {
            ShiurimSortOrFilterDialog(
                torahFilterableCallback,
                mapOfFilterCriteriaData
            )
                // I figure that it is worth the cost of passing new objects to the sort dialog to avoid the cost of
                // eventual bugs due to passing in a reference to a mutable list
                .show(fragmentManager, TAG)
            true
        }
    }

    /**
     * Sets up [SearchView] in a toolbar.
     * @param callingFromActivity the steps need to close the keyboard when the user submits are different
     * for an activity and for a [Fragment]. See https://stackoverflow.com/questions/1109022/how-do-you-close-hide-the-android-soft-keyboard-using-java
     * @param callingActivity needed for closing keyboard, [null] if not an activity
     * @param contextForClosingKeyboard ^ [null] if not a [Fragment]
     * @param viewForClosingKeyboard ^
     * */
    fun setupSearchView(
        menuInflater: MenuInflater,
        menu: Menu,
        torahFilterableCallback: TorahFilterable,
        alsoUsingFilterButton: Boolean,
        shouldInflateLayout: Boolean,
        callingFromActivity: Boolean,
        callingActivity: Activity?,
        contextForClosingKeyboard: Context?,
        viewForClosingKeyboard: View?

    ) {
        if (shouldInflateLayout) menuInflater.inflate(
            if (alsoUsingFilterButton) R.menu.search_bar_and_filter_button else R.menu.search_bar_only,
            menu
        )

        val searchView = menu.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.imeOptions = EditorInfo.IME_ACTION_DONE


        if (userWantsToCancelSearchBarOnFirstCancelButtonClick) {
            // Get the search close button image view
            val closeButton: ImageView? =
                searchView?.findViewById(R.id.search_close_btn) as ImageView?

            // Set on click listener
            closeButton?.setOnClickListener {
                Log.d(TAG, "Search close button clicked")
                //Find EditText view
                val editText = searchView?.findViewById(R.id.search_src_text) as EditText?

                //Clear the text from EditText view
                editText?.setText("")

                //Clear query
                searchView?.setQuery("", false)
                //Collapse the action view
                searchView?.onActionViewCollapsed()
                //Collapse the search widget
                menu.findItem(R.id.actionSearch).collapseActionView()
            }
        }
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (callingFromActivity) {
                    Log.d(TAG, "Keyboard closing from activity $callingActivity")
                    hideKeyboard(callingActivity!!)
                } else {
                    Log.d(
                        TAG,
                        "Keyboard closing from fragment; context = $contextForClosingKeyboard, view = $viewForClosingKeyboard"
                    )
                    hideKeyboardFromFragment(
                        contextForClosingKeyboard!!,
                        viewForClosingKeyboard!!
                    )
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                torahFilterableCallback.search(newText ?: "")
                return false
            }
        })
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideKeyboardFromFragment(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /*Not sure whether to put these in Shiur or here: */
    fun Shiur.getSpeakerReciever(): String? {
        return when (this) {
            is ShiurFullPage -> speaker
            else -> baseSpeaker
        }
    }

    fun Shiur.getCategoryReciever(): String? {
        return when (this) {
            is ShiurFullPage -> category
            else -> "TEST" //TODO request category info from server
        }
    }

    fun Shiur.getSeriesReciever(): String? {
        return when (this) {
            is ShiurFullPage -> series
            else -> "TEST"//TODO request series info from server
        }
    }

    fun android.content.res.Resources.getShiurFilterOptionString(name: String) =
        this.getString(ShiurFilterOption.valueOf(name.toUpperCase(Locale.ROOT)).nameStringResourceId)

    fun ShiurFilterOption.getShiurFilterOptionString() =
        mEntireApplicationContext.resources.getString(nameStringResourceId)

    /**
     * From [com.google.android.material.textfield.DropdownMenuEndIconDelegate]
     * */
    private fun addRippleEffectOnOutlinedLayout(
        editText: AutoCompleteTextView,
        rippleColor: Int,
        states: Array<IntArray>,
        boxBackground: MaterialShapeDrawable
    ) {
        //TODO to implement
        val editTextBackground: LayerDrawable
        val surfaceColor = MaterialColors.getColor(editText, R.attr.colorSurface)
        val rippleBackground = MaterialShapeDrawable(boxBackground.shapeAppearanceModel)
        val pressedBackgroundColor = MaterialColors.layer(rippleColor, surfaceColor, 0.1f)
        val rippleBackgroundColors = intArrayOf(pressedBackgroundColor, Color.TRANSPARENT)
        rippleBackground.fillColor = ColorStateList(states, rippleBackgroundColors)
        editTextBackground = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rippleBackground.setTint(surfaceColor)
            val colors = intArrayOf(pressedBackgroundColor, surfaceColor)
            val rippleColorStateList = ColorStateList(states, colors)
            val mask = MaterialShapeDrawable(boxBackground.shapeAppearanceModel)
            mask.setTint(Color.WHITE)
            val rippleDrawable: Drawable =
                RippleDrawable(rippleColorStateList, rippleBackground, mask)
            val layers = arrayOf(rippleDrawable, boxBackground)
            LayerDrawable(layers)
        } else {
            val layers = arrayOf<Drawable>(rippleBackground, boxBackground)
            LayerDrawable(layers)
        }
        ViewCompat.setBackground(editText, editTextBackground)
    }

    /**
     * Converts seconds ([this]) to hours, minutes, seconds format
     * @receiver seconds to convert
     * @return a [Triple] of final hour, minute, second
     * @sample (578).toHrMinSec() = (0, 9, 38)*/
    fun Int.toHrMinSec(): Triple<Int, Int, Int> {
        var hour = 0
        var minute = 0
        var second = this
        minute += (second / 60)
        hour += (minute / 60)
        second %= 60
        minute %= 60
        return Triple(hour, minute, second)
    }

    fun Triple<Int, Int, Int>.formatted(withColons: Boolean) =
        if (withColons) "${this.first}:${this.second}:${this.third}" else timeFormattedConsicely(
            this.first,
            this.second,
            this.third
        )

    fun timeFormattedConsicely(hour: Int, minute: Int, second: Int): String {
        return when {
            (second == 0) and (hour == 0) and (minute == 0) -> "$second sec"
            (second == 0) and (hour > 0) and (minute == 0) -> "$hour hr"
            (second > 0) and (hour == 0) and (minute == 0) -> "$second sec"
            (second == 0) and (hour == 0) and (minute > 0) -> "$minute min"
            (second > 0) and (hour > 0) and (minute == 0) -> "$hour hr $second sec"
            (second == 0) and (hour > 0) and (minute > 0) -> "$hour hr $minute min"
            (second > 0) and (hour == 0) and (minute > 0) -> "$minute min $second sec"
            (second > 0) and (hour > 0) and (minute > 0) -> "$hour hr $minute min $second sec"
            else -> "$hour hr $minute min $second sec" // will never happen; 2^3=8
        }
    }

    /**
     * Used for subcategory page and individual speaker page to add button to menu which says "SHIURIM"
     * and takes the user to the child shiurim page
     * */
    fun setupShiurimButton(
        menu: Menu?,
        menuInflater: MenuInflater,
        packageContext: Context,
        putExtrasLambda: Intent.() -> Unit
    ): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.speaker_page, menu)
        val viewShiurimButtton: MenuItem? = menu?.findItem(R.id.view_shiurim)
        viewShiurimButtton?.setOnMenuItemClickListener {
            val intent = Intent(packageContext, BaseShiurimPageActivity::class.java)
            intent.apply(putExtrasLambda)
            packageContext.startActivity(intent)
            true
        }
        return true
    }

    fun setupShiurimButton(
        menu: Menu?,
        menuInflater: MenuInflater,
        packageContext: Context,
        title: String?,
        shiurim: ArrayList<Parcelable>
    ) {
        val inflater = menuInflater
        inflater.inflate(R.menu.speaker_page, menu)
        val viewShiurimButtton: MenuItem? = menu?.findItem(R.id.view_shiurim)
        viewShiurimButtton?.setOnMenuItemClickListener {
            val intent = Intent(packageContext, BaseShiurimPageActivity::class.java)
            intent.apply {
                putExtra(CONSTANTS.INTENT_EXTRA_SHIURIM_PAGE_TITLE, title)
                putParcelableArrayListExtra(
                    CONSTANTS.INTENT_EXTRA_SHIURIM_PAGE_SHIURIM,
                    shiurim
                )
            }
            packageContext.startActivity(intent)
            true
        }
    }
}