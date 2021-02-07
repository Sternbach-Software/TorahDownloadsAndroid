package tech.torah.aldis.androidapp.adapters.shiurAdapter

import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
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
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.DragSelectableActivity
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import tech.torah.aldis.androidapp.dialogs.ShiurOptionsBottomSheetDialog
import tech.torah.aldis.androidapp.mEntireApplicationContext
import java.util.*
import kotlin.system.measureNanoTime

private const val TAG = "ShiurAdapter"

class ShiurAdapter(
    private val originalList: List<Shiur/*FullPage*/>,
    private val fragmentManagerForInflatingBottomSheet: FragmentManager,
    private val mDragSelectTouchListener: DragSelectTouchListener,
    private val selectionStartCallbackListener: DragSelectableActivity
) :
    RecyclerView.Adapter<ShiurAdapter.ShiurViewHolder>(), FastScroller.SectionIndexer,
    TorahFilterable {
    init {
        val mSelected: HashSet<Int> = HashSet()
        val mSelectedList = MutableList(10000 - 1) { false }
        mSelected.addAll((1..10000))
        val time1 = measureNanoTime { mSelected.contains(6893) }
        val time2 = measureNanoTime { mSelectedList[6893] }
        Log.d(TAG, "Time 1: $time1")
        Log.d(TAG, "Time 2: $time2")
    }

    //TODO consider making originalShiurFullPageList an immutable set (it never changes and it doesn't need doubles
    private val workingList: MutableList<Shiur/*FullPage*/> =
        originalList.toMutableList()
    val mSelected: HashSet<Int> =
        HashSet() //because the drag selection library uses a HashSet, i need to return a HashSet to the touch processor,listener, etc. but for the check wether a shiur is selected, I think it is more efficient to use array access on a list of booleans: namely, mSelectedList.
    val mSelectedList: MutableList<Boolean> =
        MutableList(originalList.size/*same as shiurFullPageList.size at this place in the code*/) { false } //initialize the list that every shiur entry is false TODO will this cause problems when shiurFullPageList is smaller than the original list (viz. when it is partially filtered) and an element is searched for at an index relative to the filtered list (because the index `adapterPosition` is being passed to startDragSelection) and not the original list (because this MutableList<Boolean> is based on the original dataset size? I wonder if doing this will help preserve the list of selected shiurim if the user filters the shiurim while in middle of a selection (i.e. some shiurim are selected when the user filters)
//TODO possibly consider updating mSelectedList when a filter is performed to reflect the size of the working list to prevent mSelectedList[position] from returning true because of old state.

    private val mEntireApplicationContextAsLocalVariable =
        mEntireApplicationContext //because the call to

    //mEntireApplicationContext's getter may be more expensive than a call to a local getter
    private val resources: Resources = mEntireApplicationContextAsLocalVariable.resources
    private val theme = mEntireApplicationContextAsLocalVariable.theme
    private val secondaryColor = R.color.secondary
    private val secondaryColorVariant = R.color.secondary_variant
    private val backgroundColorDay = R.color.background_day
    private val backgroundColorNight = R.color.background_night
    private val secondaryColorVariantResource = resources.getColor(secondaryColorVariant, theme)
    private val backgroundColorDayResource = resources.getColor(backgroundColorDay, theme)
    private val backgroundColorNightResource = resources.getColor(backgroundColorNight, theme)
    private val secondaryColorResource = resources.getColor(secondaryColor, theme)
//    private val accentColorTypedValue = TypedValue().also { theme.resolveAttribute(accentColor, it, true) }
//    private val defaultBackgroundColorTypedValue = TypedValue().also { theme.resolveAttribute(defaultBackgroundColor, it, true) }
//    ContextCompat.getColor(mEntireApplicationContextAsLocalVariable, accentColorTypedValue.data )
//    ContextCompat.getColor(mEntireApplicationContextAsLocalVariable, defaultBackgroundColorTypedValue.data )
//    ContextCompat.getColor(mEntireApplicationContextAsLocalVariable, defaultBackgroundColorTypedValue.data )
//    ContextCompat.getColor(mEntireApplicationContextAsLocalVariable, accentColorTypedValue.data )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiurViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_shiur_card_layout, parent, false)
        return ShiurViewHolder(v)
    }

    override fun getItemCount(): Int = workingList.size

    override fun onBindViewHolder(holder: ShiurViewHolder, position: Int) {
        if (mSelectedList.size == 0) holder.bindItem(workingList[position], false)
        else if (mSelectedList.size >= position + 1 && mSelectedList[position]) {
            holder.setIsSelectedEffect()
            holder.bindItem(workingList[position], false)
        } else holder.bindItem(workingList[position], true)
    }

    override fun getSectionText(position: Int): CharSequence =
        workingList[position].baseTitle!!.first().toString()

    override fun filter(
        constraint: String,
        shiurFilterOption: ShiurFilterOption,
        exactMatch: Boolean
    ) {
        FunctionLibrary.filter(
            constraint,
            originalList,
            workingList,
            this,
            shiurFilterOption,
            exactMatch
        )
    //TODO make a TorahAdapter interface which has a filter, sort, and reset function which has all of
    //the parameters of FunctionLibrary.filter() as properties and the adapters can
    //override the properties as needed and the callers can just call "adapter.filter()"
    //and TorahFilterable will make its default implementation call TorahAdapter.filter()
    }

    //TODO make the following functions part of an interface:

    fun toggleSelection(pos: Int) {
        if (mSelectedList[pos]) {
            mSelected.remove(pos)
            mSelectedList[pos] = false
        } else {
            mSelected.add(pos)
            mSelectedList[pos] = true
        }
        notifyItemChanged(pos)
        Log.d(TAG, "(toggleSelection)mSelected: $mSelected")
        Log.d(TAG, "(toggleSelection)mSelectedList: $mSelectedList")
    }

    fun selectRange(start: Int, end: Int, selected: Boolean) {
        for (i in start..end) {
            if (selected) {
                mSelected.add(i)
                mSelectedList[i] = true
            } else {
                mSelected.remove(i)
                mSelectedList[i] = false
            }
        }
        notifyItemRangeChanged(start, end - start + 1)
        Log.d(TAG, "(SelectRange)mSelected: $mSelected")
        Log.d(TAG, "(SelectRange)mSelectedList: $mSelectedList")
    }

    fun deselectAll() {
        // this is not beautiful...
        //TODO I think what he meant was that he could have gone through the list and manually done notifyItemChanged
        mSelected.clear()
        mSelectedList.myReplaceAll { false }
        notifyDataSetChanged()
    }

    /**
     * A modified copy of List.replaceAll(), but the latter requires API level 24
     * */
    private fun <E> MutableList<E>.myReplaceAll(operator: (E?) -> E) {
        Objects.requireNonNull(operator)
        val li: MutableListIterator<E> = this.listIterator()
        while (li.hasNext()) {
            li.set(operator(li.next()))
        }
    }

    fun selectAll() {
        for (i in 0 until workingList.size) {
            mSelected.add(i)
            mSelectedList[i] = true
        }
        notifyDataSetChanged()
    }

    override fun reset() {
        FunctionLibrary.reset(originalList, workingList, this)
    }

    override fun sort(shiurFilterOptions: List<ShiurFilterOption>, ascending: List<Boolean>) {
        FunctionLibrary.sort(workingList, this, shiurFilterOptions, ascending)
    }

    override fun sort(shiurFilterOption: ShiurFilterOption, ascending: Boolean) {
        FunctionLibrary.sort(workingList, this, shiurFilterOption, ascending)
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
                if (selectionStartCallbackListener.dragSelectModeEnabled) toggleSelection(
                    adapterPosition
                )
            }

            itemView.setOnLongClickListener {
                selectionStartCallbackListener.dragSelectModeEnabled = true
                mDragSelectTouchListener.startDragSelection(
                    adapterPosition
                )
                selectionStartCallbackListener.setSelectionIconToEnabled(true)
                true
            }
        }

        fun setIsSelectedEffect() {
//            val layers = arrayOfNulls<Drawable>(2)
//            layers[0] = ResourcesCompat.getDrawable(resources, R.drawable.ab, theme)
//            layers[1] =
//                ResourcesCompat.getDrawable(resources, R.drawable.ic_check_circle_outline, theme)
//                    ?.apply {setTint(color) }
//            val layerDrawable = LayerDrawable(layers)
//            speakerImage?.setImageDrawable(layerDrawable)
            val mode = resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)
            val night = Configuration.UI_MODE_NIGHT_YES
            val backgroundColor = if(mode==night) secondaryColorVariantResource else secondaryColorResource
            card.setCardBackgroundColor(backgroundColor)
//            shiurTitle?.text = "SELECTED" //for testing
        }

        fun bindItem(shiurFullPage: Shiur/*FullPage*/, resetColor: Boolean) {
            val mode = resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)
            val night = Configuration.UI_MODE_NIGHT_YES
            val backgroundColor = if(mode==night) backgroundColorNightResource else backgroundColorDayResource
            if (resetColor) card.setCardBackgroundColor(backgroundColor)
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

