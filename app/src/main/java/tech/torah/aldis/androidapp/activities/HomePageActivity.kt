package tech.torah.aldis.androidapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import android.view.View
import tech.torah.aldis.androidapp.R


@Suppress("UNUSED_PARAMETER")
class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page_scroll_view)
    }
    fun openSpeakerPage(v: View) {
        val intent = Intent(this@HomePageActivity, SpeakerPageActivity::class.java)
        this@HomePageActivity.startActivity(intent)
    }
    fun openRecentlyAddedShiurimPage(v: View) {
        val intent = Intent(this@HomePageActivity, RecentlyAddedShiurimPageActivity::class.java)
        this@HomePageActivity.startActivity(intent)
        /*val snackBar = Snackbar.make(it,"TO BE IMPLEMENTED",Snackbar.LENGTH_LONG).setAction("Action",null)
        snackBar.show()*/
    }
    fun openPlaylistsPage(v: View) {
        /*val intent = Intent(this@HomePageActivity, PlaylistsActivity::class.java)
        this@HomePageActivity.startActivity(intent)*/
        val snackBar = Snackbar.make(v, "TO BE IMPLEMENTED", Snackbar.LENGTH_SHORT).setAction(
            "Action",
            null
        )
        snackBar.show()
    }
    fun openFavoritesPage(v: View) {
        /*val intent = Intent(this@HomePageActivity, PlaylistsActivity::class.java)
        this@HomePageActivity.startActivity(intent)*/
        val snackBar = Snackbar.make(v, "TO BE IMPLEMENTED", Snackbar.LENGTH_SHORT).setAction(
            "Action",
            null
        )
        snackBar.show()
    }
    fun openDownloadsPage(v: View) {
        /*val intent = Intent(this@HomePageActivity, PlaylistsActivity::class.java)
        this@HomePageActivity.startActivity(intent)*/
        val snackBar = Snackbar.make(v, "TO BE IMPLEMENTED", Snackbar.LENGTH_SHORT).setAction(
            "Action",
            null
        )
        snackBar.show()
    }
    fun openHistoryPage(v: View) {
        /*val intent = Intent(this@HomePageActivity, PlaylistsActivity::class.java)
        this@HomePageActivity.startActivity(intent)*/
        val snackBar = Snackbar.make(v, "TO BE IMPLEMENTED", Snackbar.LENGTH_SHORT).setAction(
            "Action",
            null
        )
        snackBar.show()
    }
    fun openCategoriesPage(v: View) {
        val intent = Intent(this@HomePageActivity, TabsActivity::class.java)
        this@HomePageActivity.startActivity(intent)
        /* val snackBar = Snackbar.make(it,"TO BE IMPLEMENTED",Snackbar.LENGTH_SHORT).setAction("Action",null)
         snackBar.show()*/
    }
}