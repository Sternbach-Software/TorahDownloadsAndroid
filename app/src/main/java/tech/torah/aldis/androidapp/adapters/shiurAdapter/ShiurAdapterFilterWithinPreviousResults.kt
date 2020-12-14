package tech.torah.aldis.androidapp.adapters.shiurAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFullPage
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import java.util.*
//I was working on implementing "Filter within previous results", but realized that
// I did not have the head to work out the logic, so decided to put it aside and work on it later.
// I could not figure out how to make a new branch(i.e. "do it the right way"), so I just copy-pasted
// The code into this new file and renamed it, and rolled back the other file


class ShiurAdapterFilterWithinPreviousResults(private val originalShiurFullPageList: List<ShiurFullPage>) :
    RecyclerView.Adapter<ShiurAdapterFilterWithinPreviousResults.ShiurViewHolderShiurAdapterFilterWithinPreviousResults>(), FastScroller.SectionIndexer {
    //TODO consider making originalShiurFullPageList an immutable set (it never changes and it doesn't need doubles
    //TODO possibly consider modifying originalShiurFullPageList to get around concurrent moficiation execption
    //tempShiurListNotProgressiveFiltering is the list being diplayed in the fast scroller dialog
    private val tempShiurListNotProgressiveFiltering: MutableList<ShiurFullPage> =
        originalShiurFullPageList.toMutableList()

    //tempShiurListProgressiveFiltering is the cache that the shiurim will go in while they are
    // being sorted from tempShiurListNotProgressiveFiltering, and once the progressive filtering is
    // done, the cache will be cleared. This will prevent the CondurrentModificationException
    // I was getting beforehand.
    private val tempShiurListProgressiveFiltering: MutableList<ShiurFullPage> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiurViewHolderShiurAdapterFilterWithinPreviousResults {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_shiur_card_layout, parent, false)
        return ShiurViewHolderShiurAdapterFilterWithinPreviousResults(v)
    }

    override fun getItemCount(): Int = tempShiurListNotProgressiveFiltering.size

    override fun onBindViewHolder(holder: ShiurViewHolderShiurAdapterFilterWithinPreviousResults, position: Int) =
        holder.bindItems(tempShiurListNotProgressiveFiltering[position])

    override fun getSectionText(position: Int): CharSequence =
        tempShiurListNotProgressiveFiltering[position].title.first().toString()

    fun filter(tabType: TabType, constraint: String, filteringWithinPreviousResults: Boolean) {
//TODO Would it make filtering more efficient by using indices instead of full objects? Or are they just references...?
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

        if (!filteringWithinPreviousResults) tempShiurListNotProgressiveFiltering.clear()
        if (constraint.isEmpty()) {
            tempShiurListNotProgressiveFiltering.addAll(originalShiurFullPageList)
        } else {
            val filterPattern = constraint.toLowerCase(Locale.ROOT).trim()
            for (shiur in if (filteringWithinPreviousResults) tempShiurListNotProgressiveFiltering else originalShiurFullPageList) {
                val filterReciever = when (tabType) {
                    TabType.CATEGORY -> shiur.category
                    TabType.SERIES -> shiur.series
                    TabType.SPEAKER -> shiur.speaker
                    else -> ""
                }
                val meetsFilterCondition =
                    filterReciever.toLowerCase(Locale.ROOT).contains(filterPattern)
                if (filteringWithinPreviousResults && !meetsFilterCondition) {
                    tempShiurListNotProgressiveFiltering.remove(shiur)
                } else if (!filteringWithinPreviousResults && meetsFilterCondition) {
                    tempShiurListNotProgressiveFiltering.add(shiur)
                }
            }
            if(filteringWithinPreviousResults) {
                //once the temp filtering is done, do as the comment at the decleration of
                // tempShiurListProgressiveFiltering
                tempShiurListNotProgressiveFiltering.apply {
                    clear()
                    addAll(tempShiurListProgressiveFiltering)
                }
                tempShiurListProgressiveFiltering.clear()
            }
        }
        notifyDataSetChanged()
    }

    fun reset() {
        //TODO make reset more efficient by using indices.
        tempShiurListNotProgressiveFiltering.clear()
        tempShiurListNotProgressiveFiltering.addAll(originalShiurFullPageList)
        notifyDataSetChanged()
    }

    class ShiurViewHolderShiurAdapterFilterWithinPreviousResults(itemView: View) : RecyclerView.ViewHolder(itemView) {

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