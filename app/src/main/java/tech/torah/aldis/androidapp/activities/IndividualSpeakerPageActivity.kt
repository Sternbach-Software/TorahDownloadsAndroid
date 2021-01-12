package tech.torah.aldis.androidapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CONSTANTS.EXTRA_SPEAKER_DETAILS
private const val TAG = "IndividualSpeakerPageActivity"
class IndividualSpeakerPageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.individual_speaker_page)
        val listOfInformation = intent.extras?.getStringArrayList(EXTRA_SPEAKER_DETAILS)
        val name = listOfInformation?.get(0)
        val description = listOfInformation?.get(1)
        val nameTextView = findViewById<TextView>(R.id.speaker_name)
        val descriptionTextView = findViewById<TextView>(R.id.speaker_description)
        nameTextView.text = name
        descriptionTextView.text = description
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.speaker_page, menu)
        val viewShiurimButtton: MenuItem? = menu?.findItem(R.id.view_shiurim)
        viewShiurimButtton?.setOnMenuItemClickListener {
            val intent = Intent(this@IndividualSpeakerPageActivity, RecentlyAddedShiurimPageActivity::class.java)
            this@IndividualSpeakerPageActivity.startActivity(intent)
            true
        }
        return true
    }
}