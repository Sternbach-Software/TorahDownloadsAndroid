package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.adapters.shiurAdapter.ShiurAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.*
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.ShiurFullPage
import tech.torah.aldis.androidapp.dialogs.ShiurOptionsBottomSheetDialog

private lateinit var listOfSpeakerNames: MutableList<String>
private lateinit var listOfCategoryNames: MutableList<String>
private lateinit var listOfSeriesNames: MutableList<String>
private const val TAG = "RecentlyAddedShiurimPageActivity"

class RecentlyAddedShiurimPageActivity : AppCompatActivity(), TorahFilterable, HoldsShiurCard {
    private lateinit var shiurAdapter: ShiurAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)

        val recyclerView: RecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val listOfShiurim = mutableListOf(
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(speaker = "Rabbi Yehuda Adesoiuggggggggduyhuhiubuybuhwuiadhiawhd",title = "Rabbi Yehuda Adesgggggggoiudhwuiadhytgytgyiawhdeifhiuhefiefhiueifeihiefheiufhuihfeihfeihiehuifhuiehuiefhiufuiehiufhiuefeiuifeiuefin"),
            ShiurFullPage(speaker = "Rabbi Yehuda Ades"),
            ShiurFullPage(speaker = "Rabbi Yehuda Ades",category = "A"),
            ShiurFullPage(speaker = "Rabbi Yehuda Ades", category = "A"),
            ShiurFullPage(speaker = "Rabbi Yehuda Ades",category = "A",series = "B"),
            ShiurFullPage(speaker = "Rabbi Yehuda Ades",category = "A",series = "B"),
            ShiurFullPage(speaker = "Rabbi 1Yehuda Ades",category = "A",series = "B"),
            ShiurFullPage(speaker = "Rabbi 2Yehuda Ades",category = "A",series = "B"),
        )
        listOfSpeakerNames = mutableListOf()
        listOfSeriesNames = mutableListOf()
        listOfCategoryNames = mutableListOf()

        for (shiur in listOfShiurim) {
            shiur.speaker?.let { listOfSpeakerNames.add(it) }
            shiur.series?.let { listOfSeriesNames.add(it) }
            shiur.category?.let { listOfCategoryNames.add(it) }
        }
        listOfSpeakerNames = listOfSpeakerNames.toSet().toMutableList()
        listOfSeriesNames = listOfSeriesNames.toSet().toMutableList()
        listOfCategoryNames = listOfCategoryNames.toSet().toMutableList()
        //TODO I have a feeling that all of this new object creating is using a lot of RAM.
        // I do a significant amount of this also in ShiurAdapter, and anywhere which interfaces
        // with ShiurimSortOrFilterDialog.
        shiurAdapter = ShiurAdapter(listOfShiurim)
        recyclerView?.adapter = shiurAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        FunctionLibrary.setupFilterAndSearch(
            menu,
            menuInflater,
            this,
            supportFragmentManager,
            TAG,
            listOfSpeakerNames,
            listOfCategoryNames,
            listOfSeriesNames
        )
        //</editor-fold>
        return true
    }



//    override fun filter(constraint: String) = shiurAdapter.filter(constraint , tabType =  TabType.NONE)
    override fun filter(constraint: String, tabType: TabType, exactMatch: Boolean) {
    shiurAdapter.filter(constraint, tabType, exactMatch = exactMatch)
    }

    override fun reset() = shiurAdapter.reset()

    override fun openOptionsMenu(view: View): Unit {
        ShiurOptionsBottomSheetDialog().apply {
            show(supportFragmentManager, tag)
        }
    }
}
