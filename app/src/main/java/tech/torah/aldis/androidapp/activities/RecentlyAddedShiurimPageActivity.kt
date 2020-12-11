package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFullPage
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import tech.torah.aldis.androidapp.fragments.ShiurOptionsBottomSheetDialogFragment
import tech.torah.aldis.androidapp.fragments.SortOrFilterDialog
import java.util.*


private var shiurPictureCount = 0
private var drawables = listOf(
    R.drawable.a,
    R.drawable.ab,
    R.drawable.ac,
    R.drawable.ad,
    R.drawable.ae,
    R.drawable.af,
    R.drawable.ag,
    R.drawable.ah,
    R.drawable.ai,
    R.drawable.aj,
    R.drawable.ak,
    R.drawable.al,
    R.drawable.am,
    R.drawable.an,
    R.drawable.ao,
    R.drawable.ap,
    R.drawable.aq,
    R.drawable.ar,
    R.drawable.`as`,
    R.drawable.at,
    R.drawable.au,
    R.drawable.av,
    R.drawable.aw,
    R.drawable.ax,
    R.drawable.ay,
    R.drawable.az,
    R.drawable.ba,
    R.drawable.bb,
    R.drawable.bc,
    R.drawable.bd,
    R.drawable.be,
    R.drawable.bf,
    R.drawable.bg,
    R.drawable.bh,
    R.drawable.bi,
    R.drawable.bj,
    R.drawable.bk,
    R.drawable.bl,
    R.drawable.bm,
    R.drawable.bn,
    R.drawable.bo,
    R.drawable.bp,
    R.drawable.bq,
    R.drawable.br,
    R.drawable.bs,
    R.drawable.bt,
    R.drawable.bu,
    R.drawable.bv,
    R.drawable.bw,
    R.drawable.bx,
    R.drawable.by,
    R.drawable.bz,
    R.drawable.ca,
    R.drawable.cb,
    R.drawable.cc,
    R.drawable.cd,
    R.drawable.ce,
    R.drawable.cf,
    R.drawable.cg,
    R.drawable.ch,
    R.drawable.ci,
    R.drawable.cj,
    R.drawable.ck,
    R.drawable.cl,
    R.drawable.cm,
    R.drawable.cn,
)
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
            ShiurFullPage(speaker = "Rabbi Yehuda Ades"),
            ShiurFullPage(speaker = "Rabbi Gedaliah Anemer"),
            ShiurFullPage(speaker = "Rabbi David Ashear"),
            ShiurFullPage(speaker = "Rabbi Baruch Shalom HaLevi Ashlag"),
            ShiurFullPage(speaker = "Rabbi Shmuel Auerbach"),
            ShiurFullPage(speaker = "Rabbi Elkanah Austern"),
            ShiurFullPage(speaker = "Rabbi Stephen Baars"),
            ShiurFullPage(speaker = "Rabbi Shmuel Eliezer Baddiel"),
            ShiurFullPage(speaker = "Rabbi Yudi Bakst"),
            ShiurFullPage(speaker = "Rabbi Chaim Balter"),
            ShiurFullPage(speaker = "Rabbi Yitzchok Basser"),
            ShiurFullPage(speaker = "Rabbi David Ashear"),
            ShiurFullPage(speaker = "Rabbi Baruch Shalom HaLevi Ashlag"),
            ShiurFullPage(speaker = "Rabbi Shmuel Auerbach"),
            ShiurFullPage(speaker = "Rabbi Elkanah Austern"),
            ShiurFullPage(speaker = "Rabbi Stephen Baars"),
            ShiurFullPage(speaker = "Rabbi Shmuel Eliezer Baddiel"),
            ShiurFullPage(speaker = "Rabbi Yudi Bakst"),
            ShiurFullPage(speaker = "Rabbi Chaim Balter"),
            ShiurFullPage(speaker = "Rabbi Yitzchok Basser"),
            ShiurFullPage(speaker = "Rabbi David Ashear"),
            ShiurFullPage(speaker = "Rabbi Baruch Shalom HaLevi Ashlag"),
            ShiurFullPage(speaker = "Rabbi Shmuel Auerbach"),
            ShiurFullPage(speaker = "Rabbi Elkanah Austern"),
            ShiurFullPage(speaker = "Rabbi Stephen Baars"),
            ShiurFullPage(speaker = "Rabbi Shmuel Eliezer Baddiel"),
            ShiurFullPage(speaker = "Rabbi Yudi Bakst"),
            ShiurFullPage(speaker = "Rabbi Chaim Balter"),
            ShiurFullPage(speaker = "Rabbi Yitzchok Basser"),
            ShiurFullPage(speaker = "Rabbi David Ashear"),
            ShiurFullPage(speaker = "Rabbi Baruch Shalom HaLevi Ashlag"),
            ShiurFullPage(speaker = "Rabbi Shmuel Auerbach"),
            ShiurFullPage(speaker = "Rabbi Elkanah Austern"),
            ShiurFullPage(speaker = "Rabbi Stephen Baars"),
            ShiurFullPage(speaker = "Rabbi Shmuel Eliezer Baddiel"),
            ShiurFullPage(speaker = "Rabbi Yudi Bakst"),
            ShiurFullPage(speaker = "Rabbi Chaim Balter"),
            ShiurFullPage(speaker = "Rabbi Yitzchok Basser"),
            ShiurFullPage(speaker = "Rabbi David Ashear"),
            ShiurFullPage(speaker = "Rabbi Baruch Shalom HaLevi Ashlag"),
            ShiurFullPage(speaker = "Rabbi Shmuel Auerbach"),
            ShiurFullPage(speaker = "Rabbi Elkanah Austern"),
            ShiurFullPage(speaker = "Rabbi Stephen Baars"),
            ShiurFullPage(speaker = "Rabbi Shmuel Eliezer Baddiel"),
            ShiurFullPage(speaker = "Rabbi Yudi Bakst"),
            ShiurFullPage(speaker = "Rabbi Chaim Balter"),
            ShiurFullPage(speaker = "Rabbi Yitzchok Basser"),
            ShiurFullPage(speaker = "Rabbi Mordechai Becher"),
            ShiurFullPage(speaker = "Rabbi Yosef Bechhofer"),
            ShiurFullPage(speaker = "Rabbi Ian Beider"),
            ShiurFullPage(speaker = "Rabbi Berel Bell"),
            ShiurFullPage(speaker = "Rabbi Yisroel Belsky"),
            ShiurFullPage(speaker = "Rabbi Yaakov Bender"),
            ShiurFullPage(speaker = "Rabbi Yosef Berger"),
            ShiurFullPage(speaker = "Rabbi Motty Berger"),
            ShiurFullPage(speaker = "Rabbi Michael Berger"),
            ShiurFullPage(speaker = "Rabbi Moshe Bergman"),
            ShiurFullPage(speaker = "Rabbi Yitzchak Berkovits"),
            ShiurFullPage(speaker = "Rabbi Tzvi Berkowitz"),
            ShiurFullPage(speaker = "Rabbi Yitzchak Berkowitz"),
        )
        listOfSpeakerNames = mutableListOf()
        listOfSeriesNames = mutableListOf()
        listOfCategoryNames = mutableListOf()

        for (shiur in listOfShiurim) {
            listOfSpeakerNames.add(shiur.speaker)
            listOfSeriesNames.add(shiur.series)
            listOfCategoryNames.add(shiur.category)
        }
        shiurAdapter = ShiurAdapter(listOfShiurim)
        recyclerView?.adapter = shiurAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.downloads_favorites_history_pages_menu, menu)
        val filterItem: MenuItem = menu!!.findItem(R.id.filter_button)
        filterItem.setOnMenuItemClickListener {
            SortOrFilterDialog(
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

    override fun callbackFilter(tabType: TabType, data: String) {
        if (tabType == TabType.ALL) shiurAdapter.reset()
        else shiurAdapter.filter(tabType, data)
    }

    fun openOptionsMenu(v: View): Unit {
        ShiurOptionsBottomSheetDialogFragment().apply {
            show(supportFragmentManager, tag)
        }
    }
}

private class ShiurAdapter(val originalShiurFullPageList: MutableList<ShiurFullPage>) :
    RecyclerView.Adapter<ShiurAdapter.ShiurViewHolder>(), FastScroller.SectionIndexer {
    //TODO consider making originalShiurFullPageList an immutable set (it never changes and it doesn't need doubles
    val shiurFullPageList: MutableList<ShiurFullPage> = originalShiurFullPageList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiurViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_shiur_card_layout, parent, false)
        return ShiurViewHolder(v)
    }

    override fun getItemCount(): Int = shiurFullPageList.size

    override fun onBindViewHolder(holder: ShiurViewHolder, position: Int) =
        holder.bindItems(shiurFullPageList[position])

    override fun getSectionText(position: Int): CharSequence =
        shiurFullPageList[position].title.first().toString()

    fun filter(tabType: TabType, constraint: String) {
//TODO make filtering more efficient by using indices.
/*
        var completeListIndex = 0
        var filteredListIndex = 0
        while (completeListIndex < originalshiurList.size) {
            val shiur: shiur = originalshiurList[completeListIndex]
            if (shiur.name.toLowerCase(Locale.ROOT).trim().contains(constraint)) {
                if (filteredListIndex < shiurFullPageList.size) {
                    val filter: shiur = shiurFullPageList[filteredListIndex]
                    if (shiur.name != filter.name) {
                        shiurFullPageList.add(filteredListIndex, shiur)
                        notifyItemInserted(filteredListIndex)
                    }
                } else {
                    shiurFullPageList.add(filteredListIndex, shiur)
                    notifyItemInserted(filteredListIndex)
                }
                filteredListIndex++
            } else if (filteredListIndex < shiurFullPageList.size) {
                val filter: shiur = shiurFullPageList[filteredListIndex]
                if (shiur.name==filter.name) {
                    shiurFullPageList.removeAt(filteredListIndex)
                    notifyItemRemoved(filteredListIndex)
                }
            }
            completeListIndex++
        }
*/

        shiurFullPageList.clear()
        if (constraint.isEmpty()) {
            shiurFullPageList.addAll(originalShiurFullPageList)
        } else {
            val filterPattern = constraint.toLowerCase(Locale.ROOT).trim()
            for (shiur in originalShiurFullPageList) {
                val filterReciever = when (tabType) {
                    TabType.CATEGORY -> shiur.category
                    TabType.SERIES -> shiur.series
                    TabType.SPEAKER -> shiur.speaker
                    else -> ""
                }
                if (filterReciever.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                    shiurFullPageList.add(shiur)
                }
            }
        }
        notifyDataSetChanged()
    }

    fun reset() {
        //TODO make reset more efficient by using indices.
        shiurFullPageList.clear()
        shiurFullPageList.addAll(originalShiurFullPageList)
        notifyDataSetChanged()
    }

    class ShiurViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(shiurFullPage: ShiurFullPage) {
            val shiurTitle = itemView.findViewById(R.id.category_title) as TextView?
            val shiurSpeaker = itemView.findViewById(R.id.shiur_speaker) as TextView?
            if (shiurTitle != null) {
                shiurTitle.text = shiurFullPage.title
            }
            if (shiurSpeaker != null) {
                shiurSpeaker.text = shiurFullPage.speaker
            }
        }
    }
}