package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener
import com.michaelflisar.dragselectrecyclerview.DragSelectionProcessor
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.ShiurFullPage
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.adapters.shiurAdapter.ShiurAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.DragSelectableActivity
import java.util.HashSet

class IndividualPlaylistPageActivity: AppCompatActivity(), TorahFilterable, DragSelectableActivity {
    private lateinit var shiurAdapter: ShiurAdapter

    override var dragSelectModeEnabled: Boolean = false
    override var actionMenu: Menu?
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun clearSelection() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.individual_playlist_page_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Populating the recycler view and page
        val recyclerView: RecyclerView? = findViewById(R.id.playlist_items_recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val listOfPlaylists = mutableListOf(
            ShiurFullPage(speaker = "Rabbi Yehuda Ades"),
            ShiurFullPage(speaker = "Rabbi Gedaliah Anemer"),
            ShiurFullPage(speaker = "Rabbi David Ashear"),
            ShiurFullPage(speaker = "Rabbi Baruch Shalom HaLevi Ashlag"),
            ShiurFullPage(speaker = "Rabbi Shmuel Auerbach"),
            ShiurFullPage(speaker = "Rabbi Elkanah Austern"),
            ShiurFullPage(speaker = "Rabbi Stephen Baars"),
            ShiurFullPage(speaker = "Rabbi Shmuel Eliezer Baddiel"),
            ShiurFullPage(speaker = "Rabbi Yudi Bakst"),
            ShiurFullPage(speaker = "Rabbi Chaim Balter"),
            ShiurFullPage(speaker = "Rabbi Yitzchok Basser"),
            ShiurFullPage(speaker = "Rabbi David Ashear"),
            ShiurFullPage(speaker = "Rabbi Baruch Shalom HaLevi Ashlag"),
            ShiurFullPage(speaker = "Rabbi Shmuel Auerbach"),
            ShiurFullPage(speaker = "Rabbi Elkanah Austern"),
            ShiurFullPage(speaker = "Rabbi Stephen Baars"),
            ShiurFullPage(speaker = "Rabbi Shmuel Eliezer Baddiel"),
            ShiurFullPage(speaker = "Rabbi Yudi Bakst"),
            ShiurFullPage(speaker = "Rabbi Chaim Balter"),
            ShiurFullPage(speaker = "Rabbi Yitzchok Basser"),
            ShiurFullPage(speaker = "Rabbi David Ashear"),
            ShiurFullPage(speaker = "Rabbi Baruch Shalom HaLevi Ashlag"),
            ShiurFullPage(speaker = "Rabbi Shmuel Auerbach"),
            ShiurFullPage(speaker = "Rabbi Elkanah Austern"),
            ShiurFullPage(speaker = "Rabbi Stephen Baars"),
            ShiurFullPage(speaker = "Rabbi Shmuel Eliezer Baddiel"),
            ShiurFullPage(speaker = "Rabbi Yudi Bakst"),
            ShiurFullPage(speaker = "Rabbi Chaim Balter"),
            ShiurFullPage(speaker = "Rabbi Yitzchok Basser"),
            ShiurFullPage(speaker = "Rabbi David Ashear"),
            ShiurFullPage(speaker = "Rabbi Baruch Shalom HaLevi Ashlag"),
            ShiurFullPage(speaker = "Rabbi Shmuel Auerbach"),
            ShiurFullPage(speaker = "Rabbi Elkanah Austern"),
            ShiurFullPage(speaker = "Rabbi Stephen Baars"),
            ShiurFullPage(speaker = "Rabbi Shmuel Eliezer Baddiel"),
            ShiurFullPage(speaker = "Rabbi Yudi Bakst"),
            ShiurFullPage(speaker = "Rabbi Chaim Balter"),
            ShiurFullPage(speaker = "Rabbi Yitzchok Basser"),
            ShiurFullPage(speaker = "Rabbi David Ashear"),
            ShiurFullPage(speaker = "Rabbi Baruch Shalom HaLevi Ashlag"),
            ShiurFullPage(speaker = "Rabbi Shmuel Auerbach"),
            ShiurFullPage(speaker = "Rabbi Elkanah Austern"),
            ShiurFullPage(speaker = "Rabbi Stephen Baars"),
            ShiurFullPage(speaker = "Rabbi Shmuel Eliezer Baddiel"),
            ShiurFullPage(speaker = "Rabbi Yudi Bakst"),
            ShiurFullPage(speaker = "Rabbi Chaim Balter"),
            ShiurFullPage(speaker = "Rabbi Yitzchok Basser"),
            ShiurFullPage(speaker = "Rabbi Mordechai Becher"),
            ShiurFullPage(speaker = "Rabbi Yosef Bechhofer"),
            ShiurFullPage(speaker = "Rabbi Ian Beider"),
            ShiurFullPage(speaker = "Rabbi Berel Bell"),
            ShiurFullPage(speaker = "Rabbi Yisroel Belsky"),
            ShiurFullPage(speaker = "Rabbi Yaakov Bender"),
            ShiurFullPage(speaker = "Rabbi Yosef Berger"),
            ShiurFullPage(speaker = "Rabbi Motty Berger"),
            ShiurFullPage(speaker = "Rabbi Michael Berger"),
            ShiurFullPage(speaker = "Rabbi Moshe Bergman"),
            ShiurFullPage(speaker = "Rabbi Yitzchak Berkovits"),
            ShiurFullPage(speaker = "Rabbi Tzvi Berkowitz"),
            ShiurFullPage(speaker = "Rabbi Yitzchak Berkowitz"),
        )

        /*  val listOfPlaylists = mutableListOf(
            "Playlist 1",
            "Playlist 2",
            "Playlist 3",
            "Playlist 4",
            "Playlist 5",
            "Playlist 6",
            "Playlist 7",
            "Playlist 8",
            "Playlist 9",
            "Playlist 10",
            "Playlist 11",
            "Playlist 12",
            "Playlist 13",
            "Playlist 14",
            "Playlist 15",
            "Playlist 16",
            "Playlist 17",
            "Playlist 18",
            "Playlist 1",
            "Playlist 2",
            "Playlist 3",
            "Playlist 4",
            "Playlist 5",
            "Playlist 6",
            "Playlist 7",
            "Playlist 8",
            "Playlist 9",
            "Playlist 10",
            "Playlist 11",
            "Playlist 12",
            "Playlist 13",
            "Playlist 14",
            "Playlist 15",
            "Playlist 16",
            "Playlist 17",
            "Playlist 18",)*/
        val mDragSelectionProcessor = DragSelectionProcessor(object : DragSelectionProcessor.ISelectionHandler {
            override fun getSelection(): HashSet<Int> {
                return shiurAdapter.mSelected
            }

            override fun isSelected(index: Int): Boolean {
                return shiurAdapter.mSelectedList[index]
            }

            override fun updateSelection(
                start: Int,
                end: Int,
                isSelected: Boolean,
                calledFromOnStart: Boolean
            ) {
                shiurAdapter.selectRange(start, end, isSelected)
            }
        }).withMode(DragSelectionProcessor.Mode.FirstItemDependent)
        val mDragSelectTouchListener = DragSelectTouchListener().withSelectListener(mDragSelectionProcessor)
        val shiurAdapter = ShiurAdapter(listOfPlaylists, fragmentManagerForInflatingBottomSheet=supportFragmentManager,mDragSelectTouchListener,this)
        recyclerView?.adapter = shiurAdapter
    }

    /*override fun openOptionsMenu(view: View) {
        ShiurOptionsBottomSheetDialog().apply {
            show(supportFragmentManager, tag)
        }
    }*/

    override fun filter(constraint: String, shiurFilterOption: ShiurFilterOption, exactMatch: Boolean) {
        shiurAdapter.filter(constraint, shiurFilterOption, exactMatch = exactMatch)
    }

    override fun sort(shiurFilterOptions: List<ShiurFilterOption>, ascending: List<Boolean>) {
        TODO("Not yet implemented")
    }

    override fun sort(shiurFilterOption: ShiurFilterOption, ascending: Boolean) {
        TODO("Not yet implemented")
    }

    override fun reset() {
        shiurAdapter.reset()
    }


}