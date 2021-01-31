package tech.torah.aldis.androidapp.adapters.shiurAdapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.l4digital.fastscroll.FastScroller
import com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import tech.torah.aldis.androidapp.dialogs.ShiurOptionsBottomSheetDialog
import tech.torah.aldis.androidapp.mEntireApplicationContext
import java.util.*

private const val TAG = "ShiurAdapter"

class ShiurAdapter(
    private val originalShiurList: List<Shiur/*FullPage*/>,
    private val fragmentManagerForInflatingBottomSheet: FragmentManager,
    private val mDragSelectTouchListener: DragSelectTouchListener
) :
    RecyclerView.Adapter<ShiurAdapter.ShiurViewHolder>(), FastScroller.SectionIndexer,
    TorahFilterable {
    //TODO consider making originalShiurFullPageList an immutable set (it never changes and it doesn't need doubles
    private val shiurFullPageList: MutableList<Shiur/*FullPage*/> =
        originalShiurList.toMutableList()
    val mSelected: HashSet<Int> = HashSet()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiurViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_shiur_card_layout, parent, false)
        return ShiurViewHolder(v)
    }

    override fun getItemCount(): Int = shiurFullPageList.size

    override fun onBindViewHolder(holder: ShiurViewHolder, position: Int) {
        holder.bindItem(shiurFullPageList[position])
        if (mSelected.contains(position)) holder.setIsSelectedEffect()
    }

    override fun getSectionText(position: Int): CharSequence =
        shiurFullPageList[position].baseTitle!!.first().toString()

    override fun filter(
        constraint: String,
        shiurFilterOption: ShiurFilterOption,
        exactMatch: Boolean
    ) {
        FunctionLibrary.filter(
            constraint,
            originalShiurList,
            shiurFullPageList,
            this,
            shiurFilterOption,
            exactMatch
        )
    }

    fun toggleSelection(pos: Int) {
        if (mSelected.contains(pos)) mSelected.remove(pos) else mSelected.add(pos)
        notifyItemChanged(pos)
    }

    fun selectRange(start: Int, end: Int, selected: Boolean) {
        for (i in start..end) {
            if (selected) mSelected.add(i) else mSelected.remove(i)
        }
        notifyItemRangeChanged(start, end - start + 1)
    }

    fun deselectAll() {
        // this is not beautiful...
        //TODO I think what he meant was that he could have gone through the list and manually done insertedAt and removedAt
        mSelected.clear()
        notifyDataSetChanged()
    }

    fun selectAll() {
        for (i in 0 until shiurFullPageList.size) mSelected.add(i)
        notifyDataSetChanged()
    }

    override fun reset() {
        FunctionLibrary.reset(originalShiurList, shiurFullPageList, this)
    }

    override fun sort(shiurFilterOptions: List<ShiurFilterOption>, ascending: List<Boolean>) {
        FunctionLibrary.sort(shiurFullPageList, this, shiurFilterOptions, ascending)
    }

    override fun sort(shiurFilterOption: ShiurFilterOption, ascending: Boolean) {
        FunctionLibrary.sort(shiurFullPageList, this, shiurFilterOption, ascending)
    }

    inner class ShiurViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val card = itemView.findViewById(R.id.card) as MaterialCardView
        private val speakerImage = itemView.findViewById(R.id.shiur_speaker_image) as ImageView?
        private val shiurTitle = itemView.findViewById(R.id.shiur_title) as TextView?
        private val shiurSpeaker = itemView.findViewById(R.id.shiur_speaker) as TextView?
        private val expandBottomSheetButton =
            itemView.findViewById(R.id.more_options_button) as ImageView?

        init {
            itemView.setOnClickListener {
                toggleSelection(adapterPosition)
            }

            itemView.setOnLongClickListener {
                mDragSelectTouchListener.startDragSelection(adapterPosition)
                true
            }
        }

        fun setIsSelectedEffect() {
            val resources: Resources = mEntireApplicationContext.resources
//            val layers = arrayOfNulls<Drawable>(2)
            val theme = mEntireApplicationContext.theme
            val accentColor = R.color.purple_700
            val color = resources.getColor(accentColor, theme)
//            layers[0] = ResourcesCompat.getDrawable(resources, R.drawable.ab, theme)
//            layers[1] =
//                ResourcesCompat.getDrawable(resources, R.drawable.ic_check_circle_outline, theme)
//                    ?.apply {setTint(color) }
//            val layerDrawable = LayerDrawable(layers)
//            speakerImage?.setImageDrawable(layerDrawable)
            card.setBackgroundColor(color)
            shiurTitle?.text = "SELECTED"
        }

        fun bindItem(shiurFullPage: Shiur/*FullPage*/) {
            shiurTitle?.text = shiurFullPage.baseTitle
            shiurSpeaker?.text = shiurFullPage.baseSpeaker
            //TODO add circular ripple to expandBottomSheetButton and other icons
            expandBottomSheetButton?.setOnClickListener {
                ShiurOptionsBottomSheetDialog(shiurFullPage).apply {
                    show(fragmentManagerForInflatingBottomSheet, tag)
                }
            }
        }
    }
}

