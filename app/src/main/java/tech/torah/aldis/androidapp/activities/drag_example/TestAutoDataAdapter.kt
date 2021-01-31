package tech.torah.aldis.androidapp.activities.drag_example

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.mEntireApplicationContext
import java.util.*


class TestAutoDataAdapter(
    private val mContext: Context,
    private val mDataSize: Int,
    private val mDragSelectTouchListener: DragSelectTouchListener
) :
    RecyclerView.Adapter<TestAutoDataAdapter.ViewHolder>() {
    val selection: HashSet<Int> = HashSet()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.individual_playlist_and_shiur_queue_list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvText.text = position.toString()
        if (selection.contains(position)) holder.setIsSelectedEffect()
    }

    override fun getItemCount(): Int {
        return mDataSize
    }

    // ----------------------
    // Selection
    // ----------------------
    fun toggleSelection(pos: Int) {
        if (selection.contains(pos)) selection.remove(pos) else selection.add(pos)
        notifyItemChanged(pos)
    }

    fun selectRange(start: Int, end: Int, selected: Boolean) {
        for (i in start..end) {
            if (selected) selection.add(i) else selection.remove(i)
        }
        notifyItemRangeChanged(start, end - start + 1)
    }

    fun deselectAll() {
        // this is not beautiful...
        //TODO I think what he meant was that he could have gone through the list and manually done insertedAt and removedAt
        selection.clear()
        notifyDataSetChanged()
    }

    fun selectAll() {
        for (i in 0 until mDataSize) selection.add(i)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setIsSelectedEffect() {
            val r: Resources = mEntireApplicationContext.resources
            val layers = arrayOfNulls<Drawable>(2)
            layers[0] = ResourcesCompat.getDrawable(r, R.drawable.ab, mEntireApplicationContext.theme)
            layers[1] = ResourcesCompat.getDrawable(r, R.drawable.ab, mEntireApplicationContext.theme)
            val layerDrawable = LayerDrawable(layers)
//            testimage.setImageDrawable(layerDrawable)
            itemView.setBackgroundColor(Color.RED)
        }

        var tvText: TextView = itemView.findViewById<View>(R.id.text_view) as TextView

        init {
            itemView.setOnClickListener{
                toggleSelection(adapterPosition)
            }

            itemView.setOnLongClickListener{
                mDragSelectTouchListener.startDragSelection(adapterPosition)
                true
            }
        }
    }

}