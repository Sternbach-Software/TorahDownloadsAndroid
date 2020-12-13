package tech.torah.aldis.androidapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFullPage
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import java.util.*

class ShiurAdapter(val originalShiurFullPageList: MutableList<ShiurFullPage>) :
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