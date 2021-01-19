package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.dialogs.ShiurimSortOrFilterDialog

private const val TAG = "TabsActivity"

class TabsActivity : AppCompatActivity(), TorahFilterable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val button = Button(applicationContext)
        setContentView(button)
        /*findViewById<Button>(R.id.buttonShowDialog)*/button.setOnClickListener { showDialog() }
    }
        private fun showDialog() {
//            val dialogFragment = ShiurimSortOrFilterDialog(this,mapOf("a" to listOf("a","b"),"b" to listOf("c","d"),"c" to listOf("e","f")))
//            dialogFragment.show(supportFragmentManager, TAG)
        }


    override fun filter(constraint: String, shiurFilterOption: ShiurFilterOption, exactMatch: Boolean) {
//        findViewById<TextView>(R.id.textView).text = constraint
        TODO("Not yet implemented")
    }

    override fun reset() {
        //TODO("Not yet implemented")
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