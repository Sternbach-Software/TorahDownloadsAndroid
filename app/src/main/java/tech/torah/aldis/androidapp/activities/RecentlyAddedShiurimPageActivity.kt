package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.adapters.shiurAdapter.ShiurAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFullPage
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import tech.torah.aldis.androidapp.dialogs.ShiurOptionsBottomSheetDialog
import tech.torah.aldis.androidapp.dialogs.ShiurimSortOrFilterDialog

private lateinit var listOfSpeakerNames: MutableList<String>
private lateinit var listOfCategoryNames: MutableList<String>
private lateinit var listOfSeriesNames: MutableList<String>
private const val TAG = "RecentlyAddedShiurimPageActivity"

class RecentlyAddedShiurimPageActivity : AppCompatActivity(), TorahFilterable {
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
            listOfSpeakerNames.add(shiur.speaker)
            listOfSeriesNames.add(shiur.series)
            listOfCategoryNames.add(shiur.category)
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
        val inflater = menuInflater
        inflater.inflate(R.menu.downloads_favorites_history_pages_menu, menu)
        val filterItem: MenuItem = menu!!.findItem(R.id.filter_button)
        filterItem.setOnMenuItemClickListener {
            ShiurimSortOrFilterDialog(
                this,
                listOfSpeakerNames.toList(),
                listOfCategoryNames.toList(),
                listOfSeriesNames.toList()
            )
                // I figure that it is worth the cost of passing new objects to the sort dialog to avoid the cost of
                // eventual bugs due to passing in a reference to a mutable list
                .show(supportFragmentManager, TAG)
            true
        }
        return true
    }

    override fun callbackFilter(
        tabType: TabType,
        data: String,
        filterWithinPreviousResults: Boolean
    ) {
        if (tabType == TabType.ALL) shiurAdapter.reset()
        else shiurAdapter.filter(tabType, data/*,filterWithinPreviousResults*/)
    }

    fun openOptionsMenu(@Suppress("UNUSED_PARAMETER")v: View): Unit {
        ShiurOptionsBottomSheetDialog().apply {
            show(supportFragmentManager, tag)
        }
    }
}