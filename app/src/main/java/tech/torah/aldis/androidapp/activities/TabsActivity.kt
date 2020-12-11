package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.fragments.SortOrFilterDialog

private const val TAG = "TabsActivity"

class TabsActivity : AppCompatActivity(), TorahFilterable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testing_layout)
        findViewById<Button>(R.id.buttonShowDialog).setOnClickListener { showDialog() }
    }
        private fun showDialog() {
            val dialogFragment = SortOrFilterDialog(this,listOf("a","b"),listOf("c","d"),listOf("e","f"))
            dialogFragment.show(supportFragmentManager, TAG)
        }

    override fun callbackFilter(tabType: TabType, data: String) {
        findViewById<TextView>(R.id.textView).text = data
    }

/*
        val sectionsPagerAdapter =
            shiurim?.let { SectionsPagerAdapter(this, supportFragmentManager, it, false) }
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager) * /

        /* val viewPager: ViewPager2? = findViewById(R.id.view_pager)
         tabLayout = findViewById(R.id.tabs)
         if (viewPager != null) {
             TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                 tab.text = "Card.DECK[position].toString()"
             }.attach()
         }*/
    }*/
}