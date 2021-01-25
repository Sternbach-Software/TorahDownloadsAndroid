package tech.torah.aldis.androidapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import tech.torah.aldis.androidapp.R

private const val TAG = "HomePageActivity"
class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page_scroll_view)

        findViewById<MaterialButton>(R.id.open_speaker_page_button).setOnClickListener {
            val intent = Intent(this@HomePageActivity, ListOfSpeakerPageActivity::class.java)
            this@HomePageActivity.startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.open_categories_page_button).setOnClickListener {
            val intent = Intent(this@HomePageActivity, ParentCategoriesPageActivity::class.java)
            this@HomePageActivity.startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.open_downloads_page_button).setOnClickListener {
            val intent = Intent(this@HomePageActivity, DownloadsPageActivity::class.java)
            this@HomePageActivity.startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.open_favorites_page_button).setOnClickListener {
            val intent = Intent(this@HomePageActivity, FavoritesPageActivity::class.java)
            this@HomePageActivity.startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.open_history_page_button).setOnClickListener {
            val intent = Intent(this@HomePageActivity, HistoryPageActivity::class.java)
            this@HomePageActivity.startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.open_playlists_page_button).setOnClickListener {
            val intent = Intent(this@HomePageActivity, ListOfPlaylistsPageActivity::class.java)
            this@HomePageActivity.startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.open_recently_added_page_button).setOnClickListener {
            val intent = Intent(this@HomePageActivity, RecentlyAddedShiurimPageActivity::class.java)
            this@HomePageActivity.startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.open_testing_page_button).setOnClickListener {
            val intent = Intent(this@HomePageActivity, ParallelNestedScrollingActivity::class.java)
            this@HomePageActivity.startActivity(intent)
        }
        findViewById<MaterialButton>(R.id.open_testing_page_2_button).setOnClickListener {
            val intent = Intent(this@HomePageActivity, TestingActivity::class.java)
            this@HomePageActivity.startActivity(intent)
        }
    }
    /*val snackBar = Snackbar.make(it,"TO BE IMPLEMENTED",Snackbar.LENGTH_LONG).setAction("Action",null)
      snackBar.show()*/
}