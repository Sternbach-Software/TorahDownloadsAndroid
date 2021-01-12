package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.HoldsShiurCard
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFullPage
import tech.torah.aldis.androidapp.dialogs.ShiurOptionsBottomSheetDialog


private const val TAG = "ShiurimPageActivity"

class ShiurimPageActivity : AppCompatActivity(), HoldsShiurCard {
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
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
            ShiurFullPage(),
        )

        shiurAdapter = ShiurAdapter(listOfShiurim)
        recyclerView?.adapter = shiurAdapter
    }

    override fun openOptionsMenu(view: View): Unit {
        ShiurOptionsBottomSheetDialog().apply {
            show(supportFragmentManager, tag)
        }
    }
    private class ShiurAdapter(originalShiurFullPageList: MutableList<ShiurFullPage>) :
        RecyclerView.Adapter<ShiurAdapter.ShiurViewHolder>(), FastScroller.SectionIndexer {

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

        class ShiurViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItems(shiurFullPage: ShiurFullPage) {
                val shiurTitle = itemView.findViewById(R.id.shiur_title) as TextView?
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
}