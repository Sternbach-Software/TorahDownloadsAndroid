package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.adapters.playlistAdapter.PlaylistAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Playlist
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable

private const val TAG = "ListOfPlaylistsPageActivity"
class ListOfPlaylistsPageActivity: AppCompatActivity(), TorahFilterable {
    private lateinit var playlistAdapter: PlaylistAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)
        val recyclerView: RecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val listOfPlaylists = listOf(
            Playlist(playlistName = "Playlist 1"),
            Playlist(playlistName = "Playlist 2"),
            Playlist(playlistName = "Playlist 3"),
            Playlist(playlistName = "Playlist 4"),
            Playlist(playlistName = "Playlist 5"),
            Playlist(playlistName = "Playlist 6"),
            Playlist(playlistName = "Playlist 7"),
            Playlist(playlistName = "Playlist 8"),
            Playlist(playlistName = "Playlist 9"),
            Playlist(playlistName = "Playlist 10"),
            Playlist(playlistName = "Playlist 11"),
            Playlist(playlistName = "Playlist 12"),
            Playlist(playlistName = "Playlist 13"),
            Playlist(playlistName = "Playlist 14"),
            Playlist(playlistName = "Playlist 15"),
            Playlist(playlistName = "Playlist 16"),
            Playlist(playlistName = "Playlist 17"),
            Playlist(playlistName = "Playlist 18"),
            Playlist(playlistName = "Playlist 19")
        )


        playlistAdapter = PlaylistAdapter(listOfPlaylists)
        recyclerView?.adapter = playlistAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       FunctionLibrary.setupFilterAndSearch(menu,menuInflater,this,supportFragmentManager,TAG,
           listOf(""), listOf(""),listOf(""))
        return true
    }

    fun openOptionsMenu(view: View) {}
  /*  override fun filter(constraint: String) {
        TODO("Not yet implemented")
    }*/

    override fun filter(constraint: String, tabType: TabType) {
        TODO("Not yet implemented")
    //       playlistAdapter.filter(constraint , tabType = tabType)
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}