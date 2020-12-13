package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.ShiurAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFullPage
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable

class DownloadsPageActivity: AppCompatActivity(), TorahFilterable {
    private lateinit var shiurAdapter: ShiurAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)
        //Populating the recycler view and page
        val recyclerView: RecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val listOfShiurim = mutableListOf(
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
        val listOfSpeakerNames = mutableListOf<String>()
        val listOfSeriesNames = mutableListOf<String>()
        val listOfCategoryNames = mutableListOf<String>()

        for (shiur in listOfShiurim) {
            listOfSpeakerNames.add(shiur.speaker)
            listOfSeriesNames.add(shiur.series)
            listOfCategoryNames.add(shiur.category)
        }
        val shiurAdapter = ShiurAdapter(listOfShiurim)
        recyclerView?.adapter = shiurAdapter
    }
    override fun callbackFilter(tabType: TabType, data: String) {
        if (tabType == TabType.ALL) shiurAdapter.reset()
        else shiurAdapter.filter(tabType, data)
    }
}
