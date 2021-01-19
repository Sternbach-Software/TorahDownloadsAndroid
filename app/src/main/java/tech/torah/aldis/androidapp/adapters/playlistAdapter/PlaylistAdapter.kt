package tech.torah.aldis.androidapp.adapters.playlistAdapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.activities.IndividualPlaylistPageActivity
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Playlist
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable

private const val TAG = "PlaylistAdapter"

class PlaylistAdapter(private val originalListOfPlaylists: List<Playlist>) :
    RecyclerView.Adapter<PlaylistAdapter.ViewHolder>(), TorahFilterable {
    private val temporaryListOfPlaylists = originalListOfPlaylists.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playlistTitle: MaterialTextView
        val playlistSubtitle: MaterialTextView

        init {
            view.setOnClickListener {
                Log.d(TAG, "Element $adapterPosition clicked.")
                val intent = Intent(view.context, IndividualPlaylistPageActivity::class.java)
            view.context.startActivity(intent)}
            playlistTitle = view.findViewById(R.id.list_item_title)
            playlistSubtitle = view.findViewById(R.id.subtitle)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.individual_playlist_and_shiur_queue_list_item_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d(TAG, "Element $position set.")
        val currentPlaylist = originalListOfPlaylists[position]
        val title = currentPlaylist.playlistName
        //23 shiurim • 4 completed
        val subtitle =
            "${currentPlaylist.totalNumberOfShiurim} shiurim • ${currentPlaylist.numberOfCompletedShiurim} completed"

        viewHolder.playlistTitle.text = title
        viewHolder.playlistTitle.text = subtitle
        //for some reason Lint is giving a warning of "Do not concatenate text displayed
        // with setText. Use resource string with placeholders." when I inline the val "subtitle". Not sure why that should be.
    }

    override fun getItemCount(): Int = originalListOfPlaylists.size

    override fun filter(constraint: String, shiurFilterOption: ShiurFilterOption, exactMatch: Boolean) {
        FunctionLibrary.filter(
            constraint,
            originalListOfPlaylists,
            temporaryListOfPlaylists,
            this,
            shiurFilterOption,
            exactMatch
        )
    }

    override fun reset() {
        //TODO would it make reset more efficient by using indices?
        temporaryListOfPlaylists.clear()
        temporaryListOfPlaylists.addAll(originalListOfPlaylists)
        notifyDataSetChanged()
    }
}