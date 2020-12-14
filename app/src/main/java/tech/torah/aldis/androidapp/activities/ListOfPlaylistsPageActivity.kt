package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.adapters.playlistAdapter.PlaylistAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Playlist
import tech.torah.aldis.androidapp.adapters.shiurAdapter.ShiurAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable

private const val TAG = "ListOfPlaylistsPageActivity"
class ListOfPlaylistsPageActivity: AppCompatActivity(), TorahFilterable {
    private lateinit var shiurAdapter: ShiurAdapter
    private lateinit var playlistAdapter: PlaylistAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)
        //Populating the recycler view and page
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
    override fun callbackFilter(
        tabType: TabType,
        data: String,
        filterWithinPreviousResults: Boolean
    ) {
        if (tabType == TabType.ALL) shiurAdapter.reset()
        else shiurAdapter.filter(tabType, data/*,filterWithinPreviousResults*/)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.downloads_favorites_history_pages_menu, menu)
        val searchItem: MenuItem = menu!!.findItem(R.id.actionSearch)
        val searchView: SearchView = searchItem.actionView as SearchView
        val filterItem: MenuItem = menu!!.findItem(R.id.filter_button)
        filterItem.setOnMenuItemClickListener {
//            ShiurimSortOrFilterDialog(
//                this,
//                listOfSpeakerNames.toList(),
//                listOfCategoryNames.toList(),
//                listOfSeriesNames.toList()
//            )
//                // I figure that it is worth the cost of passing new objects to the sort dialog to avoid the cost of
//                // eventual bugs due to passing in a reference to a mutable list
//                .show(supportFragmentManager, TAG)
            true
        }
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                playlistAdapter.filter(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                playlistAdapter.filter(newText ?: "")
                return false
            }
        })
        return true
    }
}