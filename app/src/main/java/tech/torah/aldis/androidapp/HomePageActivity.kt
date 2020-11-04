package tech.torah.aldis.androidapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class HomePageActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
setContentView(R.layout.home_page)
        val speakersButton = findViewById<MaterialButton>(R.id.speakers_button)
        val recentlyAddedButton = findViewById<MaterialButton>(R.id.recently_added_button)
        val myPlaylistsButton = findViewById<MaterialButton>(R.id.my_playlists_button)
        val categoriesButton = findViewById<MaterialButton>(R.id.categories_button)
        speakersButton.setOnClickListener {
            val intent = Intent(this@HomePageActivity, SpeakerPageActivity::class.java)
            this@HomePageActivity.startActivity(intent)
        }
        recentlyAddedButton.setOnClickListener {
            val intent = Intent(this@HomePageActivity, ShiurimPageActivity::class.java)
            this@HomePageActivity.startActivity(intent)
            /*val snackBar = Snackbar.make(it,"TO BE IMPLEMENTED",Snackbar.LENGTH_LONG).setAction("Action",null)
            snackBar.show()*/
        }
        myPlaylistsButton.setOnClickListener {/*
            val intent = Intent(this@HomePageActivity, PlaylistsActivity::class.java)
            this@HomePageActivity.startActivity(intent)*/
            val snackBar = Snackbar.make(it,"TO BE IMPLEMENTED",Snackbar.LENGTH_LONG).setAction("Action",null)
            snackBar.show()        }
        categoriesButton.setOnClickListener {/*
            val intent = Intent(this@HomePageActivity, CategoriesPageActivity::class.java)
            this@HomePageActivity.startActivity(intent)*/
            val snackBar = Snackbar.make(it,"TO BE IMPLEMENTED",Snackbar.LENGTH_LONG).setAction("Action",null)
            snackBar.show()
        }
    }
}

