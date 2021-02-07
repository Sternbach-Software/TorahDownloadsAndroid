package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener
import com.michaelflisar.dragselectrecyclerview.DragSelectionProcessor
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.adapters.shiurAdapter.ShiurAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.*
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.getCategoryReciever
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.getSeriesReciever
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.getSpeakerReciever
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import tech.torah.aldis.androidapp.mEntireApplicationContext
import java.util.HashSet


open class BaseShiurimPageActivity : AppCompatActivity(), TorahFilterable, DragSelectableActivity{
    private lateinit var mDragSelectTouchListener: DragSelectTouchListener
    open val TAG = "BaseShiurimPageActivity"
    protected lateinit var shiurAdapter: ShiurAdapter
    private var menu: Menu? = null
    open lateinit var listOfShiurim: MutableList<Shiur>
    open var pageTitle = "Shiurim"
    private val mMode = DragSelectionProcessor.Mode.FirstItemDependent //TODO make this a setting the user can change
    private var mDragSelectionProcessor: DragSelectionProcessor? = null
    protected open val scrollingSpeed = 60


    override var dragSelectModeEnabled: Boolean = false
    override var actionMenu: Menu? = null //TODO will this always be null?
    override fun clearSelection() {
        shiurAdapter.deselectAll()
    }

    /*
    TODO figure out how to handle the boolean conditions HAS_ATTACHMENT and HAS_DESCRIPTION.
      list of "yes" or "no" based on index?*/
    private lateinit var listOfSpeakerNames: MutableList<String>
    private lateinit var listOfCategoryNames: MutableList<String>
    private lateinit var listOfSeriesNames: MutableList<String>
    private lateinit var listOfLanguageNames: MutableList<String>
    private lateinit var listOfDatesUploaded: MutableList<String>
    private lateinit var listOfDatesAddedToPersonalCollection: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)
        intent.getStringExtra(CONSTANTS.INTENT_EXTRA_SHIURIM_PAGE_TITLE)?.let{pageTitle = it}
        supportActionBar?.title = pageTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listOfShiurim =
            (intent.getParcelableArrayExtra(CONSTANTS.INTENT_EXTRA_SHIURIM_PAGE_SHIURIM) as ArrayList<Shiur>?)?.toMutableList()
                ?: CONSTANTS.sampleListOfShiurim.toMutableList().doubled().doubled() as MutableList<Shiur>
        val recyclerView: RecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        listOfSpeakerNames = mutableListOf()
        listOfSeriesNames = mutableListOf()
        listOfCategoryNames = mutableListOf()

        for (shiur in listOfShiurim) {
            shiur.getSpeakerReciever()?.let { listOfSpeakerNames.add(it) }
            shiur.getSeriesReciever()?.let { listOfSeriesNames.add(it) }
            shiur.getCategoryReciever()?.let { listOfCategoryNames.add(it) }
        }

        listOfSpeakerNames = listOfSpeakerNames.toSet().toMutableList()
        listOfSeriesNames = listOfSeriesNames.toSet().toMutableList()
        listOfCategoryNames = listOfCategoryNames.toSet().toMutableList()
        //TODO I have a feeling that all of this new object creating is using a lot of RAM.
        // I do a significant amount of this also in ShiurAdapter, and anywhere which interfaces
        // with ShiurimSortOrFilterDialog.

        val selectionHandler = object :
            DragSelectionProcessor.ISelectionHandler {
            override fun getSelection(): HashSet<Int> {
                return shiurAdapter.mSelected
            }

            override fun isSelected(index: Int): Boolean {
                return shiurAdapter.mSelectedList[index]
            }

            override fun updateSelection(
                start: Int,
                end: Int,
                isSelected: Boolean,
                calledFromOnStart: Boolean
            ) {
                shiurAdapter.selectRange(start, end, isSelected)
            }
        }
        mDragSelectionProcessor = DragSelectionProcessor(selectionHandler).withMode(mMode)
        mDragSelectTouchListener = DragSelectTouchListener()
            .withSelectListener(mDragSelectionProcessor)
            .withMaxScrollDistance(scrollingSpeed)
        recyclerView?.addOnItemTouchListener(mDragSelectTouchListener)
        shiurAdapter = ShiurAdapter(
            listOfShiurim,
            fragmentManagerForInflatingBottomSheet = supportFragmentManager,
            mDragSelectTouchListener,
            this
        )
        recyclerView?.adapter = shiurAdapter
        recyclerView?.layoutAnimation = AnimationUtils.loadLayoutAnimation(this,R.anim.bottom_up)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu.let{
            this.menu = it
            actionMenu = it
        }
        FunctionLibrary.setupFilterAndSearch(
            menu,
            menuInflater,
            this,
            supportFragmentManager,
            TAG,
            mapOf(
                /*getTabTypeString(*/
                ShiurFilterOption.SPEAKER/*)*/ to listOfSpeakerNames,
                /*getTabTypeString(*/
                ShiurFilterOption.CATEGORY/*)*/ to listOfCategoryNames,
                /*getTabTypeString(*/
                ShiurFilterOption.SERIES/*)*/ to listOfSeriesNames,
                ShiurFilterOption.LENGTH to listOf(
                    "1",
                    "5",
                    "17",
                    "22",
                    "19",
                    "81",
                    "155",
                    "187",
                    "300",
                    "500",
                    "1000",
                    "2000",
                    "3000",
                    "4000",
                    "5000",
                    "32400"
                ),
                ShiurFilterOption.HAS_ATTACHMENT to listOf(),//will be handled by dialog
                ShiurFilterOption.HAS_DESCRIPTION to listOf(),//will be handled by dialog
                ShiurFilterOption.LANGUAGE to listOf("Hebrew", "English"),
            ),
            true,
            this,
            null,
            null
        )
        FunctionLibrary.setupStartSelectionButton(menu, this)
        //</editor-fold>
        return true
    }


    //    override fun filter(constraint: String) = shiurAdapter.filter(constraint , shiurFilterOption =  ShiurFilterOption.TITLE)
    override fun filter(
        constraint: String,
        shiurFilterOption: ShiurFilterOption,
        exactMatch: Boolean
    ) =
        shiurAdapter.filter(constraint, shiurFilterOption, exactMatch = exactMatch)


    override fun reset() {
        clearEditTextAndCollapseSearchView()
        shiurAdapter.reset()
    }

    override fun sort(shiurFilterOptions: List<ShiurFilterOption>, ascending: List<Boolean>) {
        shiurAdapter.sort(shiurFilterOptions, ascending)
    }

    override fun sort(shiurFilterOption: ShiurFilterOption, ascending: Boolean) {
        shiurAdapter.sort(shiurFilterOption, ascending)
    }

    private fun clearEditTextAndCollapseSearchView() {
        //TODO potential WET spot
        (menu?.findItem(R.id.actionSearch)?.actionView as SearchView?)?.apply {
            Log.d(TAG, "Search close button clicked")
            (this.findViewById(R.id.search_src_text) as EditText?)?.setText("") //Clear the text from EditText view
            setQuery("", true) //Clear query and submit to close the keyboard and
            // not do two filters (one for the onQueryTextChange and the shiurAdapter.reset() below)
            onActionViewCollapsed() //Collapse the action view
        }
        menu?.findItem(R.id.actionSearch)?.collapseActionView() //Collapse the search widget
    }


/*    override fun getDragSelectModeEnabled(): Boolean {
        return dragSelectModeEnabled
    }

    override fun setDragSelectModeEnabled(enabled: Boolean) {
        dragSelectModeEnabled = enabled
    }*/
}

private fun <E> MutableList<E>.doubled(): MutableList<E> {
    return this.toMutableList().also{for(element in this) it.add(element)}
}
