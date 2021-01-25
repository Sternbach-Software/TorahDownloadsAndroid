package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.adapters.shiurAdapter.ShiurAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CONSTANTS
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.getCategoryReciever
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.getSeriesReciever
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.getSpeakerReciever
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.ShiurFullPage


open class BaseShiurimPageActivity : AppCompatActivity(), TorahFilterable {
    open val TAG = "BaseShiurimPageActivity"
    protected lateinit var shiurAdapter: ShiurAdapter
    private var menu: Menu? = null
    open lateinit var listOfShiurim: MutableList<Shiur>
    open var pageTitle = "Shiurim"

    /*
    TODO figure out how to handle the boolean conditions HAS_ATTACHMENT and HAS_DESCRIPTION. list of "yes" or "no" based on index?*/
    private lateinit var listOfSpeakerNames: MutableList<String>
    private lateinit var listOfCategoryNames: MutableList<String>
    private lateinit var listOfSeriesNames: MutableList<String>
    private lateinit var listOfLanguageNames: MutableList<String>
    private lateinit var listOfDatesUploaded: MutableList<String>
    private lateinit var listOfDatesAddedToPersonalCollection: MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)
        pageTitle = intent.getStringExtra(CONSTANTS.INTENT_EXTRA_SHIURIM_PAGE_TITLE).toString()
        supportActionBar?.title = pageTitle
        listOfShiurim =
            (intent.getParcelableArrayExtra(CONSTANTS.INTENT_EXTRA_SHIURIM_PAGE_SHIURIM) as ArrayList<Shiur>?)?.toMutableList()
                ?: CONSTANTS.sampleListOfShiurim.toMutableList() as MutableList<Shiur>
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
        shiurAdapter = ShiurAdapter(listOfShiurim, fragmentManagerForInflatingBottomSheet=supportFragmentManager)
        recyclerView?.adapter = shiurAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
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
        //</editor-fold>
        return true
    }


    //    override fun filter(constraint: String) = shiurAdapter.filter(constraint , shiurFilterOption =  ShiurFilterOption.NONE)
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
}