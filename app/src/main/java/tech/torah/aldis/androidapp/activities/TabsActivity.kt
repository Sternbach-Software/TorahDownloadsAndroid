package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.SortOrFilterFullScreenDialog
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CallbackListener


class TabsActivity : AppCompatActivity(), CallbackListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testing_layout)
                findViewById<Button>(R.id.buttonShowDialog).setOnClickListener { showDialog() }
            }
            private fun showDialog() {
                val dialogFragment = SortOrFilterFullScreenDialog(this)
                dialogFragment.show(supportFragmentManager, "TorahDownloadsAndroid")
            }
            override fun onDataReceived(data: String) {
                findViewById<TextView>(R.id.textView).text = data
            }

        }


/*
        val a: Array<Shiur> = intent.getSerializableExtra("SHIURIM") as Array<Shiur>
        shiurim = a.toCollection(ArrayList())


        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, shiurim,false)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)*/

        /*val viewPager: ViewPager2? = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)
        if (viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = "Card.DECK[position].toString()"
            }.attach()
        }*/
/*
    }
}*/
