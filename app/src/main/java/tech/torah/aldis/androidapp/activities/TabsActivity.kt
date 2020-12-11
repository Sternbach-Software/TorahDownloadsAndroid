package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.SectionsPagerAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Shiur

private const val TAG = "TabsActivity"
class TabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testing_layout)
                findViewById<Button>(R.id.buttonShowDialog).setOnClickListener { showDialog() }
            }
            private fun showDialog() {
                val dialogFragment = SortOrFilterFullScreenDialog(this)
                dialogFragment.show(supportFragmentManager, TAG)
            }
            override fun onDataReceived(tabType: TabType, data: String) {
                findViewById<TextView>(R.id.textView).text = data
            }

}


        val sectionsPagerAdapter =
            shiurim?.let { SectionsPagerAdapter(this, supportFragmentManager, it,false) }
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)*/

       /* val viewPager: ViewPager2? = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)
        if (viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = "Card.DECK[position].toString()"
            }.attach()
        }*/
    }
}