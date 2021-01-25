package tech.torah.aldis.androidapp.adapters.shiurAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import tech.torah.aldis.androidapp.dialogs.ShiurOptionsBottomSheetDialog

private const val TAG = "ShiurAdapter"
class ShiurAdapter(private val originalShiurList: List<Shiur/*FullPage*/>, private val fragmentManagerForInflatingBottomSheet: FragmentManager) :
    RecyclerView.Adapter<ShiurAdapter.ShiurViewHolder>(), FastScroller.SectionIndexer, TorahFilterable {
    //TODO consider making originalShiurFullPageList an immutable set (it never changes and it doesn't need doubles
    private val shiurFullPageList: MutableList<Shiur/*FullPage*/> = originalShiurList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiurViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_shiur_card_layout, parent, false)
        return ShiurViewHolder(v)
    }

    override fun getItemCount(): Int = shiurFullPageList.size

    override fun onBindViewHolder(holder: ShiurViewHolder, position: Int) =
        holder.bindItem(shiurFullPageList[position])

    override fun getSectionText(position: Int): CharSequence =
        shiurFullPageList[position].baseTitle!!.first().toString()

    override fun filter(
        constraint: String,
        shiurFilterOption: ShiurFilterOption,
        exactMatch: Boolean) {
        FunctionLibrary.filter(
            constraint,
            originalShiurList,
            shiurFullPageList,
            this,
            shiurFilterOption,
            exactMatch
        )
    }

    override fun reset() {
       FunctionLibrary.reset(originalShiurList,shiurFullPageList, this)
    }

    inner class ShiurViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(shiurFullPage: Shiur/*FullPage*/) {
            val shiurTitle = itemView.findViewById(R.id.shiur_title) as TextView?
            val shiurSpeaker = itemView.findViewById(R.id.shiur_speaker) as TextView?
            val expandBottomSheetButton = itemView.findViewById<ImageView>(R.id.more_options_button)
                shiurTitle?.text = shiurFullPage.baseTitle
                shiurSpeaker?.text = shiurFullPage.baseSpeaker
                //TODO add circular ripple to expandBottomSheetButton and other icons
            expandBottomSheetButton.setOnClickListener{
                ShiurOptionsBottomSheetDialog(shiurFullPage).apply {
                    show(fragmentManagerForInflatingBottomSheet, tag)
                }
            }
        }
    }
}