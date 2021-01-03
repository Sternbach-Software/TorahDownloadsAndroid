package tech.torah.aldis.androidapp.adapters.shiurAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFullPage
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType

private const val TAG = "ShiurAdapter"
class ShiurAdapter(private val originalShiurFullPageList: List<ShiurFullPage>) :
    RecyclerView.Adapter<ShiurAdapter.ShiurViewHolder>(), FastScroller.SectionIndexer {
    //TODO consider making originalShiurFullPageList an immutable set (it never changes and it doesn't need doubles
    private val shiurFullPageList: MutableList<ShiurFullPage> = originalShiurFullPageList.toMutableList()

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

    fun filter(constraint: String, filterWithinPreviousResults: Boolean = false, tabType: TabType) {
        FunctionLibrary.filter(
            constraint,
            originalShiurFullPageList,
            shiurFullPageList,
            this,
            filterWithinPreviousResults,
            tabType= tabType,
            exactMatch = false
        )
    }

    fun reset() {
       FunctionLibrary.reset(originalShiurFullPageList,shiurFullPageList, this)
    }

    class ShiurViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(shiurFullPage: ShiurFullPage) {
            val shiurTitle = itemView.findViewById(R.id.shiur_title) as TextView?
            val shiurSpeaker = itemView.findViewById(R.id.shiur_speaker) as TextView?
//            val rectangle = ResourcesCompat.getDrawable(itemView.context.resources,R.drawable.card_progress_indicator,itemView.context.theme)
//            rectangle?.setBounds(1,2,3,4)
//            Log.d(TAG,"bounds 1,2,3,4")
//            itemView.background = rectangle
            if (shiurTitle != null) {
                shiurTitle.text = shiurFullPage.title
            }
            if (shiurSpeaker != null) {
                shiurSpeaker.text = shiurFullPage.speaker
            }
        }
    }
}