package tech.torah.aldis.androidapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "PlaylistAdapter"
class PlaylistAdapter(private val mDataSet: List<String>) :
    RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textView: TextView

        init {
            v.setOnClickListener { Log.d("", "Element $adapterPosition clicked.") }
            textView = v.findViewById(R.id.playlist_name_text_view)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.individual_playlist_list_item_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d("", "Element $position set.")

        viewHolder.textView.text = mDataSet[position]
    }

    override fun getItemCount(): Int = mDataSet.size
}