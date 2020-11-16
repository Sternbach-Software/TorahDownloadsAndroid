package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Shiur
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurOptionsBottomSheetDialogFragment


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
private const val TAG = "RecentlyAddedShiurimPageActivity"

class RecentlyAddedShiurimPageActivity : AppCompatActivity() {
    private lateinit var shiurAdapter: ShiurAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)

        val recyclerView: RecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val listOfShiurim = mutableListOf(
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
            Shiur(),
        )

        shiurAdapter = ShiurAdapter(listOfShiurim)
        recyclerView?.adapter = shiurAdapter
    }

    /*   override fun onCreateOptionsMenu(menu: Menu?): Boolean {
           val inflater = menuInflater
           inflater.inflate(R.menu.shiur_page_menu, menu)
           val searchItem: MenuItem = menu!!.findItem(R.id.actionSearch)
           val searchView: SearchView = searchItem.actionView as SearchView
           searchView.imeOptions = EditorInfo.IME_ACTION_DONE
           searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
               override fun onQueryTextSubmit(query: String?): Boolean {
                   shiurAdapter.filter(query?:"")
                   return false
               }

               override fun onQueryTextChange(newText: String?): Boolean {
                   shiurAdapter.filter(newText?:"")
                   return false
               }
           })
           return true
       }
}
*/
    fun openOptionsMenu(v: View): Unit {
        ShiurOptionsBottomSheetDialogFragment().apply {
            show(supportFragmentManager, tag)
        }
    }
    private class ShiurAdapter(originalShiurList: MutableList<Shiur>) :
        RecyclerView.Adapter<ShiurAdapter.ShiurViewHolder>(), FastScroller.SectionIndexer {

        val shiurList: MutableList<Shiur> = originalShiurList.toMutableList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiurViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.individual_shiur_card_layout, parent, false)
            return ShiurViewHolder(v)
        }

        override fun getItemCount(): Int = shiurList.size

        override fun onBindViewHolder(holder: ShiurViewHolder, position: Int) =
            holder.bindItems(shiurList[position])

        override fun getSectionText(position: Int): CharSequence =
            shiurList[position].title.first().toString()
/*
    fun filter(constraint: String){

*/
/*
        var completeListIndex = 0
        var filteredListIndex = 0
        while (completeListIndex < originalshiurList.size) {
            val shiur: shiur = originalshiurList[completeListIndex]
            if (shiur.name.toLowerCase(Locale.ROOT).trim().contains(constraint)) {
                if (filteredListIndex < shiurList.size) {
                    val filter: shiur = shiurList[filteredListIndex]
                    if (shiur.name != filter.name) {
                        shiurList.add(filteredListIndex, shiur)
                        notifyItemInserted(filteredListIndex)
                    }
                } else {
                    shiurList.add(filteredListIndex, shiur)
                    notifyItemInserted(filteredListIndex)
                }
                filteredListIndex++
            } else if (filteredListIndex < shiurList.size) {
                val filter: shiur = shiurList[filteredListIndex]
                if (shiur.name==filter.name) {
                    shiurList.removeAt(filteredListIndex)
                    notifyItemRemoved(filteredListIndex)
                }
            }
            completeListIndex++
        }
*/
/*

        shiurList.clear()
        if (constraint.isEmpty()) {
            shiurList.addAll(originalshiurList)
        } else {
            val filterPattern = constraint.toLowerCase(Locale.ROOT).trim()
            for (shiur in originalshiurList) {
                if (shiur.name.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                    shiurList.add(shiur)
                }
            }
        }
        notifyDataSetChanged()
    }
*/

        class ShiurViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItems(shiur: Shiur) {
                val shiurTitle = itemView.findViewById(R.id.category_title) as TextView?
                val shiurSpeaker = itemView.findViewById(R.id.shiur_speaker) as TextView?
                if (shiurTitle != null) {
                    shiurTitle.text = shiur.title
                }
                if (shiurSpeaker != null) {
                    shiurSpeaker.text = shiur.speaker
                }
            }
        }
    }
}