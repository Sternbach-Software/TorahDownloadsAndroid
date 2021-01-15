package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.adapters.shiurAdapter.ShiurAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.HoldsShiurCard
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.ShiurFullPage
import tech.torah.aldis.androidapp.dialogs.ShiurOptionsBottomSheetDialog



private lateinit var listOfSpeakerNames: MutableList<String>
private lateinit var listOfCategoryNames: MutableList<String>
private lateinit var listOfSeriesNames: MutableList<String>
open class BaseShiurimPageActivity : AppCompatActivity(), TorahFilterable, HoldsShiurCard {
    open val TAG = "BaseShiurimPageActivity"
    protected lateinit var shiurAdapter: ShiurAdapter
    open lateinit var listOfShiurim:MutableList<Shiur>
    open val pageTitle = "Shiurim"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)
        supportActionBar?.title = pageTitle
        //TODO figure out how to customize the title and list of shiurim by getting the intent extras if they exist
        listOfShiurim = mutableListOf(
            ShiurFullPage(title = "Halachos of Neir Havdala; Emulating HASHEM's Chesed  (Agudah Parsha Shiur Beraishis  5777)",speaker = "Rabbi Yehuda Ades",category="Chayei Sarah"),
            ShiurFullPage(title = "Halachos of Neir Havdala; Emulating HASHEM's Chesed  (Agudah Parsha Shiur Beraishis  5777)",speaker = "Rabbi Gedaliah Anemer",category="Chayei Sarah"),
            ShiurFullPage(title = "Languages- Parshas Beraishis 5779",speaker = "Rabbi David Ashear",category="Chayei Sarah"),
            ShiurFullPage(title = "Languages- Parshas Beraishis 5779",speaker = "Rabbi Baruch Shalom HaLevi Ashlag",category="Chayei Sarah"),
            ShiurFullPage(title = "Parshas B01 Beraishis 5776",speaker = "Rabbi Shmuel Auerbach",category="Chayei Sarah"),
            ShiurFullPage(title = "Parshas B01 Beraishis 5779",speaker = "Rabbi Elkanah Austern",category="Toldos"),
            ShiurFullPage(title = "Parshas B01 Beraishis 5780",speaker = "Rabbi Stephen Baars",category="Toldos"),
            ShiurFullPage(title = "Parshas B01 Bereshis 5773",speaker = "Rabbi Shmuel Eliezer Baddiel",category="Toldos"),
            ShiurFullPage(title = "Why We left Gan Eden; Teshuva is Now, and Defining Ourselves  (Parshas Beraishis 5777)",speaker = "Rabbi Yudi Bakst",category="Toldos"),
            ShiurFullPage(title = "Why We left Gan Eden; Teshuva is Now, and Defining Ourselves  (Parshas Beraishis 5777)",speaker = "Rabbi Chaim Balter",category="Toldos"),
            ShiurFullPage(title = "Yom Hashishi; Purpose and Function- Parshas Beraishis 5780",speaker = "Rabbi Yitzchok Basser",category="Toldos"),
            ShiurFullPage(title = "Yom Hashishi; Purpose and Function- Parshas Beraishis 5780.MP3",speaker = "Rabbi David Ashear",category="Toldos"),
            ShiurFullPage(title = "Bracha on the Keshes and Thunder and Lightning  (Agudah Parsha Shiur Noach 5779)",speaker = "Rabbi Baruch Shalom HaLevi Ashlag",category="Toldos"),
            ShiurFullPage(title = "Bracha on the Keshes and Thunder and Lightning  (Agudah Parsha Shiur Noach 5779)",speaker = "Rabbi Shmuel Auerbach",category="Toldos"),
            ShiurFullPage(title = "Chassidus and Tzidkus; Ahavah and Yirah- Parshas Noach 5779",speaker = "Rabbi Elkanah Austern",category="Toldos"),
            ShiurFullPage(title = "Chassidus and Tzidkus; Ahavah and Yirah- Parshas Noach 5779",speaker = "Rabbi Stephen Baars",category="Toldos"),
            ShiurFullPage(title = "Dor Haflagah and History of the World Until Our Generation; 5 Explanations (Parshas Noach 5777)",speaker = "Rabbi Shmuel Eliezer Baddiel",category="Toldos"),
            ShiurFullPage(title = "Dor Haflagah and History of the World Until Our Generation; 5 Explanations (Parshas Noach 5777)",speaker = "Rabbi Yudi Bakst",category="Toldos"),
            ShiurFullPage(title = "Nation-States and Checks and Balances; Seeing Humanity in Others- Parshas Noach 5780",speaker = "Rabbi Chaim Balter",category="Toldos"),
            ShiurFullPage(title = "Nation-States and Checks and Balances; Seeing Humanity in Others- Parshas Noach 5780",speaker = "Rabbi Yitzchok Basser",category="Toldos"),
            ShiurFullPage(title = "Parshas B02 Noach (2) 5779",speaker = "Rabbi David Ashear",category="Vayeitzei"),
            ShiurFullPage(title = "Parshas B02 Noach 5773",speaker = "Rabbi Baruch Shalom HaLevi Ashlag",category="Vayeitzei"),
            ShiurFullPage(title = "Parshas B02 Noach 5775",speaker = "Rabbi Shmuel Auerbach",category="Vayeitzei"),
            ShiurFullPage(title = "Parshas B02 Noach 5778",speaker = "Rabbi Elkanah Austern",category="Vayeitzei"),
            ShiurFullPage(title = "Parshas B02 Noach 5779",speaker = "Rabbi Stephen Baars",category="Vayeitzei"),
            ShiurFullPage(title = "Parshas B02 Noach 5780",speaker = "Rabbi Shmuel Eliezer Baddiel",category="Vayeitzei"),
            ShiurFullPage(title = "Parshas Noach 5777",speaker = "Rabbi Yudi Bakst",category="Vayeitzei"),
            ShiurFullPage(title = "Vegetarianism-Parshas Noach 5778",speaker = "Rabbi Chaim Balter",category="Vayeitzei"),
            ShiurFullPage(title = "Vegetarianism-Parshas Noach 5778",speaker = "Rabbi Yitzchok Basser",category="Vayeitzei"),
            ShiurFullPage(title = "Vegetarianism; Halacha and Hashkafa (Agudah Parsha Shiur Parshas Noach 5778)",speaker = "Rabbi David Ashear",category="Vayeitzei"),
            ShiurFullPage(title = "Vegetarianism; Halacha and Hashkafa (Agudah Parsha Shiur Parshas Noach 5778)",speaker = "Rabbi Baruch Shalom HaLevi Ashlag",category="Vayeitzei"),
            ShiurFullPage(title = "Bris and Os Bris- Parshas Lech Lecha 5779",speaker = "Rabbi Shmuel Auerbach",category="Vayeitzei"),
            ShiurFullPage(title = "Bris and Os Bris- Parshas Lech Lecha 5779",speaker = "Rabbi Elkanah Austern",category="Vayeitzei"),
            ShiurFullPage(title = "Parshas B03 Lech Lecha 5773",speaker = "Rabbi Stephen Baars",category="Vayishlach"),
            ShiurFullPage(title = "Parshas B03 Lech Lecha 5774",speaker = "Rabbi Shmuel Eliezer Baddiel",category="Vayishlach"),
            ShiurFullPage(title = "Parshas B03 Lech Lecha 5775",speaker = "Rabbi Yudi Bakst",category="Vayishlach"),
            ShiurFullPage(title = "Parshas B03 Lech Lecha 5776",speaker = "Rabbi Chaim Balter",category="Vayishlach"),
            ShiurFullPage(title = "Parshas B03 Lech Lecha 5778",speaker = "Rabbi Yitzchok Basser",category="Vayishlach"),
            ShiurFullPage(title = "Parshas B03 Lech Lecha 5779",speaker = "Rabbi David Ashear",category="Vayishlach"),
            ShiurFullPage(title = "Parshas B03 Lech Lecha 5780",speaker = "Rabbi Baruch Shalom HaLevi Ashlag",category="Vayishlach"),
            ShiurFullPage(title = "Self Esteem and Emunah- Parshas Lech Lecha 5780",speaker = "Rabbi Shmuel Auerbach",category="Vayishlach"),
            ShiurFullPage(title = "Simcha and Schok-Parshas Lech Lecha 5778",speaker = "Rabbi Elkanah Austern",category="Vayishlach"),
            ShiurFullPage(title = "Simcha and Schok-Parshas Lech Lecha 5778",speaker = "Rabbi Stephen Baars",category="Vayishlach"),
            ShiurFullPage(title = "Bikur Cholim; Halacha and Hashkafa (Agudah Parsha Shiur Parshas Vayera 5778)",speaker = "Rabbi Shmuel Eliezer Baddiel",category="Vayishlach"),
            ShiurFullPage(title = "Hachnasas Orchim (Agudah Parsha Shiur Vayera 5777)",speaker = "Rabbi Yudi Bakst",category="Vayishlach"),
            ShiurFullPage(title = "Hachnasas Orchim (Agudah Parsha Shiur Vayera 5777)",speaker = "Rabbi Chaim Balter",category="Vayishlach"),
            ShiurFullPage(title = "Kiddush Hashem and Chillul Hashem- Parshas Vayeira 5779",speaker = "Rabbi Yitzchok Basser",category="Vayishlach"),
            ShiurFullPage(title = "Kiddush Hashem and Chillul Hashem- Parshas Vayeira 5779",speaker = "Rabbi Mordechai Becher",category="Vayishlach"),
            ShiurFullPage(title = "Parshas  Vayera 5777",speaker = "Rabbi Yosef Bechhofer",category="Vayishlach"),
            ShiurFullPage(title = "Parshas B04 Vayeira 5773",speaker = "Rabbi Ian Beider",category="Vayishlach"),
            ShiurFullPage(title = "Parshas B04 VaYeira 5774",speaker = "Rabbi Berel Bell",category="Vayeshev"),
            ShiurFullPage(title = "Parshas B04 Vayera 5775",speaker = "Rabbi Yisroel Belsky",category="Vayeshev"),
            ShiurFullPage(title = "Parshas B04 Vayera 5778",speaker = "Rabbi Yaakov Bender",category="Vayeshev"),
            ShiurFullPage(title = "Parshas B04 Vayera 5780",speaker = "Rabbi Yosef Berger",category="Vayeshev"),
            ShiurFullPage(title = "Sederes Bnai Torah 01-Shimush Talmidei Chachamim(Vayera 5773)",speaker = "Rabbi Motty Berger",category="Vayeshev"),
            ShiurFullPage(title = "Mishnah Berurah Siman 352 Hakorei b'Sefer v'Nisgalgal meReshus l'reshus Seif 2; Siman 353 Dinei Zizin b'Reshus HaRabim Seif 1-2",speaker = "Rabbi Michael Berger"),
            ShiurFullPage(title = "Mishnah Berurah Siman 353 Dinei Zizin b'Reshus HaRabim Seif 2-3; Siman 354 Dinei Bor v'Ashpa BeReshus Harabim Seif 1",speaker = "Rabbi Moshe Bergman"),
            ShiurFullPage(title = "Mishnah Berurah Siman Siman 310 Dinei Muktzeh Seif 7 5778",speaker = "Rabbi Yitzchak Berkovits"),
            ShiurFullPage(title = "Mishnah Berurah Siman Siman 310 Dinei Muktzeh Seif 7-9",speaker = "Rabbi Tzvi Berkowitz"),
            ShiurFullPage(title = "Mishnah Berurah Siman Siman 311 Dinei Mais and Tiltul Min Hatzad Seif 1-2",speaker = "Rabbi Yitzchak Berkowitz"),
        )
        val recyclerView: RecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        listOfSpeakerNames = mutableListOf()
        listOfSeriesNames = mutableListOf()
        listOfCategoryNames = mutableListOf()

        for (shiur in listOfShiurim as MutableList<ShiurFullPage>) {
            shiur.speaker?.let { listOfSpeakerNames.add(it) }
            shiur.series?.let { listOfSeriesNames.add(it) }
            shiur.category?.let { listOfCategoryNames.add(it) }
        }
        listOfSpeakerNames = listOfSpeakerNames.toSet().toMutableList()
        listOfSeriesNames = listOfSeriesNames.toSet().toMutableList()
        listOfCategoryNames = listOfCategoryNames.toSet().toMutableList()
        //TODO I have a feeling that all of this new object creating is using a lot of RAM.
        // I do a significant amount of this also in ShiurAdapter, and anywhere which interfaces
        // with ShiurimSortOrFilterDialog.
        shiurAdapter = ShiurAdapter(listOfShiurim as MutableList<ShiurFullPage>)
        recyclerView?.adapter = shiurAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        FunctionLibrary.setupFilterAndSearch(
            menu,
            menuInflater,
            this,
            supportFragmentManager,
            TAG,
            listOfSpeakerNames,
            listOfCategoryNames,
            listOfSeriesNames
        )
        //</editor-fold>
        return true
    }



    //    override fun filter(constraint: String) = shiurAdapter.filter(constraint , tabType =  TabType.NONE)
    override fun filter(constraint: String, tabType: TabType, exactMatch: Boolean) =
        shiurAdapter.filter(constraint, tabType, exactMatch = exactMatch)


    override fun reset() = shiurAdapter.reset()

    override fun openOptionsMenu(v: View) {
        ShiurOptionsBottomSheetDialog().apply {
            show(supportFragmentManager, tag)
        }
    }
}