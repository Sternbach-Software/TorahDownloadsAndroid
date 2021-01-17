package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.SectionsPagerAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CONSTANTS
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.ShiurFullPage


class MoreFromThisActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout

    private lateinit var shiurim: ArrayList<Shiur>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tabs_layout)


        val a:java.util.ArrayList<Shiur>? = intent.getParcelableArrayListExtra<Shiur>(CONSTANTS.INTENT_EXTRA_MORE_FROM_THIS_CATEGORY) //TODO implement this
        shiurim = a!!


        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, shiurim,true)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        /*val viewPager: ViewPager2? = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)
        if (viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = "Card.DECK[position].toString()"
            }.attach()
        }*/
    }
}