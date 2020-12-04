 package tech.torah.aldis.androidapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.l4digital.fastscroll.FastScrollView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CallbackListener
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilter
import java.util.*
import kotlin.system.measureNanoTime


/**
 * This is the dialog that will be used for filtering the Downloads, Favorites, and History pages
 * */
private const val TAG = "DownloadsFavoritesHistoryFilterDialogFragment"
var selectedListItem = ""
lateinit var selectedListItemTextView: MaterialTextView
lateinit var progressiveFilterExplanationImageButton: ImageButton
lateinit var filterButton: MaterialButton
lateinit var cancelButton: MaterialButton
lateinit var filterRadioButton: RadioButton
lateinit var fastScrollerSelectButton: MaterialButton
lateinit var fastScrollerCancelButton: MaterialButton
lateinit var fastScrollerDeselectButton: MaterialButton
lateinit var sortRadioButton: RadioButton
lateinit var sortOrderTextInputLayout: TextInputLayout
lateinit var sortOrderDropdown: AutoCompleteTextView
lateinit var filterCriterionChooser: AutoCompleteTextView
lateinit var individualSpeakerCategorySeriesChooser: TextInputLayout
lateinit var individualSpeakerCategorySeriesChooserAutoCompleteTextView: AutoCompleteTextView
lateinit var tabTypeBeingDisplayed: TabType
lateinit var fastScrollerDialogListItems: List<String>
lateinit var listOfAvailableSpeakers: List<String>
lateinit var listOfAvailableCategories: List<String>
lateinit var listOfAvailableSeries: List<String>

val listOfFilterCriterion: List<String> = listOf("Speaker", "Category", "Series")
val listOfPossibleListItems: List<List<String>> by lazy {listOf(
    listOfAvailableSpeakers,
    listOfAvailableCategories,
    listOfAvailableSeries
)}

class SortOrFilterFullScreenDialog(private val callbackListener: CallbackListener) :
    DialogFragment() {
//TODO add parameter in primary constructor for list of possible speakers, categories, and series.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = true
        return inflater.inflate(R.layout.simple_sort_or_filter_dialog, container, false)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //<editor-fold variable initializations>
        progressiveFilterExplanationImageButton =
            view.findViewById(R.id.progressive_filter_explanation_image_button)
        filterButton = view.findViewById(R.id.filter_button_simple)
        cancelButton = view.findViewById(R.id.cancel_button_simple)
        filterRadioButton = view.findViewById(R.id.filter_radio_button_simple)
        sortRadioButton = view.findViewById(R.id.sort_radio_button_simple)
        sortOrderTextInputLayout = view.findViewById(R.id.sort_order_text_input_layout_simple)
        sortOrderDropdown = view.findViewById(R.id.sort_order_dropdown_simple)
        filterCriterionChooser = view.findViewById(R.id.filter_criterion_chooser)
        individualSpeakerCategorySeriesChooser =
            view.findViewById(R.id.individual_speaker_category_series_chooser_text_input_layout)
        individualSpeakerCategorySeriesChooserAutoCompleteTextView =
            view.findViewById(R.id.individual_speaker_category_series_chooser_auto_complete_text_view)

        listOfAvailableSpeakers = listOf(
            "Rabbi Yehuda Ades",
            "Rabbi Gedaliah Anemer",
            "Rabbi David Ashear",
            "Rabbi Baruch Shalom HaLevi Ashlag",
            "Rabbi Shmuel Auerbach",
            "Rabbi Elkanah Austern",
            "Rabbi Stephen Baars",
            "Rabbi Shmuel Eliezer Baddiel",
            "Rabbi Yudi Bakst",
            "Rabbi Chaim Balter",
            "Rabbi Yitzchok Basser",
            "Rabbi Mordechai Becher",
            "Rabbi Yosef Bechhofer",
            "Rabbi Ian Beider",
            "Rabbi Berel Bell",
            "Rabbi Yisroel Belsky",
            "Rabbi Yaakov Bender",
            "Rabbi Yosef Berger",
            "Rabbi Motty Berger",
            "Rabbi Michael Berger",
            "Rabbi Moshe Bergman",
            "Rabbi Yitzchak Berkovits",
            "Rabbi Tzvi Berkowitz",
            "Rabbi Yitzchak Berkowitz",
            "Rabbi Yehoshua Berman",
            "Rabbi Shmuel Yaakov Bernstein",
            "Rabbi Gershon Bertman",
            "Rabbi Chaim Biberfeld",
            "Rabbi Jeffrey Bienenfeld",
            "Rabbi Moshe Chaim Biron",
            "Rabbi Moshe Chaim Blate",
            "Rabbi Aharon Yosef Bloch",
            "Rabbi Tzvi Block",
            "Rabbi Yoram Bogacz",
            "Rabbi David Botton",
            "Rabbi Nachum Braverman",
            "Rabbi Yitzchak Breitowitz",
            "Rabbi Shlomo Brevda",
            "Rabbi Dovid Brodsky",
            "Rabbi Chaim Mendel Brodsky",
            "Rabbi Feivel Brody",
            "Rabbi Yisroel Brog",
            "Rabbi Elya Brudny",
            "Rabbi Avraham Brussel",
            "Rabbi Reuven Buckler",
            "Rabbi Amos Bunim",
            "Rabbi Ozzie Burnham",
            "Rabbi Chaim Burston",
            "Rabbi Yehoshua Chaitovsky",
            "Rabbi Yitzchak Charner",
            "Rabbi Aryeh Citron",
            "Rabbi Yitzchak Cohen",
            "Rabbi Simcha Cohen",
            "Rabbi Zev Cohen",
            "Rabbi Dovid Cohen",
            "Rabbi Daniel Coren",
            "Rabbi Zalman Corlin",
            "Rabbi Avishai David",
            "Rabbi Moshe Drew",
            "Rabbi Nissan Dovid Dubov",
            "Rabbi Eliezer Eichorn",
            "Rabbi Tzvi Einsteder",
            "Rabbi Yosef Elefant",
            "Rabbi Gedalia Elfeinbein",
            "Rabbi Yechezkel Elias",
            "Rabbi Yosef Shalom Elyashiv",
            "Rabbi Menachem Engel",
            "Rabbi Yitzy Erps",
            "Rabbi Yisroel Fabian",
            "Rabbi Eytan Feiner",
            "Rabbi Reuven Feinstein",
            "Rabbi Moshe Feinstein",
            "Rabbi Dovid Feinstein",
            "Rabbi Reuvain Feinstien",
            "Rabbi Aaron Feldman",
            "Rabbi Emanuel Feldman",
            "Rabbi Ilan Feldman",
            "Rabbi Chaim Fessel",
            "Rabbi Avraham Chaim Feuer",
            "Rabbi Nosson Tzvi Finkel",
            "Rabbi Chaim Flom",
            "Rabbi Yissocher Frand",
            "Rabbi Michoel Frank",
            "Rabbi Tzvi Frank",
            "Rabbi Mordechai Frankel",
            "Rabbi Daniel Freitag",
            "Rabbi Yechezkel Freundlich",
            "Rabbi Yaacov Fried",
            "Rabbi Moshe Friedman",
            "Rabbi Michoel Friedman",
            "Rabbi Netanel Friedman",
            "Rabbi Binyomin Friedman",
            "Rabbi Naftali Ganzweig",
            "Rabbi Aaron Garfinkel",
            "Rabbi Levi Gelbfish",
            "Rabbi Mordechai Gifter",
            "Rabbi Beinish Ginsburg",
            "Rabbi Yisroel Glassberg",
            "Rabbi Daniel Glatstein",
            "Rabbi Gedalya Glatt",
            "Rabbi Zvi Goldberg",
            "Rabbi Moshe Goldberger",
            "Rabbi Menashe Goldberger",
            "Rabbi Chaim Goldberger",
            "Rabbi Azriel Chaim Goldfein",
            "Rabbi Tzvi Goode",
            "Rabbi Dovid Gottlieb",
            "Rabbi Levi Graiser",
            "Rabbi Avraham Greenbaum",
            "Rabbi Naftali Greenberg",
            "Rabbi Dovid Grossman",
            "Rabbi Yitzhak Grossman",
            "Rabbi Akiva Grunblatt",
            "Rabbi Lazer Gurkow",
            "Rabbi Yaacov Haber",
            "Rabbi Avi Haber",
            "Rabbi Yekusiel Yehudah Halberstam",
            "Rabbi Dov Halperin",
            "Rabbi Chaim Halpern",
            "Rabbi Moshe Harizy",
            "Rabbi Moshe Hauer",
            "Rabbi Dovid Heber",
            "Rabbi Yosi Heber",
            "Rabbi Moshe Heinemann",
            "Rabbi Aaron Hersh",
            "Rabbi Moshe Herzog",
            "Rabbi Amram Hess",
            "Rabbi Chaim Hilewitz",
            "Reb Dovid Hill",
            "Rabbi Yaakov Hillel",
            "Rabbi Yisroel Mayer Hoberman",
            "Rabbi Yaakov Homnick",
            "Rabbi Yaakov Hopfer",
            "Rabbi Yakov Horowitz",
            "Rabbi Yitzchak Hutner",
            "Rabbi David Hyatt",
            "Rabbi Shmuel Irons",
            "Rabbi Shimon Isaacson",
            "Rabbi Yitzchok Isbee",
            "Rabbi Yehoshua Juravel",
            "Rabbi Dov Kahan",
            "Rabbi Ari Kahn",
            "Rabbi Yudah Kahn",
            "Rabbi Aharon Kahn",
            "Rabbi Yosef Kalatsky",
            "Rabbi Shmuel Kamenetsky",
            "Rabbi Yaakov Kamenetzky",
            "Rabbi Avrohom Kanarek",
            "Rabbi Dovid Kaplan",
            "Rabbi Aryeh Kaplan",
            "Rabbi Nissan Kaplan",
            "Rabbi Yaakov Karmel",
            "Rabbi Reuven Kasierer",
            "Rabbi Zev Katz",
            "Reb Eitan Katz",
            "Rabbi Yaakov Moshe Katz",
            "Rabbi Yosef Katz",
            "Rabbi Dovid Katzenstein",
            "Rabbi Aharon Kaufman",
            "Rabbi Lawrence Kelemen",
            "Rabbi Leib Kelemen",
            "Rabbi Eli Kerzner",
            "Rabbi Mendel Kessin",
            "Rabbi Moshe Don Kestenbaum",
            "Rabbi Shmuel Khoshkerman",
            "Rabbi Avraham Kilstein",
            "Rabbi Mordechai Kirshenbaum",
            "Rabbi Reuven Chaim Klein",
            "Rabbi Avrohom Kosman",
            "Rabbi Aharon Kotler",
            "Rabbi Yitzy Kowalsky",
            "Rabbi Dan Krancer",
            "Rabbi Eliezer Kreiser",
            "Rabbi Eliezer Krohn",
            "Rabbi Paysach Krohn",
            "Rabbi Avrohom Krohn",
            "Rabbi Usher Laifer",
            "Rabbi Nechemia Langer",
            "Rabbi Levi Langer",
            "Rabbi Aron Chaim Lapidoth",
            "Rabbi Moishe Dovid Lebovits",
            "Rabbi Aryeh Lebowitz",
            "Rabbi Zev Leff",
            "Rabbi Michel Yehuda Lefkowitz",
            "Rabbi Henoch Leibowitz",
            "Rabbi Akiva Leiman",
            "Rabbi Shnayer Leiman",
            "Rabbi Yaakov Leonard",
            "Rabbi Reuven Leuchter",
            "Rabbi Menachem Levine",
            "Rabbi Binyamin Levine",
            "Rabbi Reuven Levitt",
            "Rabbi Daniel Levy",
            "Rabbi Yankel Lieberman",
            "Rabbi Moshe Tuvia Lieff",
            "Rabbi Mordechai Linzer",
            "Rabbi Haskel Lookstein",
            "Rabbi Ahron Lopiansky",
            "Rabbi Chaim Malinowitz",
            "Rabbi Zalman Manela",
            "Rabbi Eli Mansour",
            "Rabbi Hadar Margolin",
            "Rabbi Daniel Mechanic",
            "Rabbi Moshe Meiselman",
            "Rabbi Yaakov Mendelson",
            "Rabbi Chaim Mendelson",
            "Rabbi Raphael Mendlowitz",
            "Rabbi Moe Mernick",
            "Rabbi Yacov Metz",
            "Rabbi Tzvi Miller",
            "Rabbi Avigdor Miller",
            "Rabbi Shlomo Miller",
            "Reb Yehoshua Mond",
            "Rabbi Avrohom Morgenstern",
            "Rabbi Abba Zvi Naiman",
            "Rabbi Sheftel Neuberger",
            "Rabbi Shraga Neuberger",
            "Rabbi Mayer Neuberger",
            "Rabbi Doniel Neustadt",
            "Rabbi Avigdor Neventzal",
            "Rabbi Moshe New",
            "Rabbi Menachem Nissel",
            "Rabbi Noach Oelbaum",
            "Rabbi Dovid Ordman",
            "Rabbi Dovid Orlofsky",
            "Rabbi Noach Orlowek",
            "Rabbi Avraham Pam",
            "Rabbi Shlomo Perlstein",
            "Rabbi Hillel Yosef Pfeiffer",
            "Rabbi Eli Pielet",
            "Rabbi Shimshon Dovid Pincus",
            "Rabbi Zelig Pliskin",
            "Rabbi Shloime Pollak",
            "Rabbi Mordechai Pollock",
            "Rabbi Baruch Ber Povarsky",
            "Rabbi Doniel Pransky",
            "Rabbi Gamliel Rabinowitz",
            "Rabbi Moshe Radner",
            "Rabbi Yishai Rasowsky",
            "Rabbi Dovid Regensberg",
            "Rabbi Naftali Reich",
            "Rabbi Eli Reich",
            "Rabbi Hershel Reichman",
            "Rabbi Shmuel Reichman",
            "Rabbi Eliyahu Reingold",
            "Rabbi Baruch Reis",
            "Rabbi Yisroel Reisman",
            "Rabbi Yona Reiss",
            "Rabbi Mordechai Rhine",
            "Rabbi Gershon Ribner",
            "Rabbi Jonathan Rietti",
            "Rabbi Dovid Rosenbaum",
            "Rabbi Yonoson Rosenblum",
            "Rabbi Yosef Rosenholtz",
            "Rabbi Philip Rosenthal",
            "Rabbi Aryeh Rottman",
            "Rabbi Yochanan Rudensky",
            "Rabbi Yaakov Yitzchok Ruderman",
            "Rabbi Zave Rudman",
            "Rabbi Mattisyahu Salomon",
            "Rabbi Yosef Saltzman",
            "Rabbi Yosef Samberg",
            "Rabbi Nachum Sauer",
            "Rabbi Fischel Schachter",
            "Rabbi Hershel Schachter",
            "Rabbi Aharon Schechter",
            "Rabbi Yitzchok Schechter",
            "Rabbi Yitzchak Scher",
            "Rabbi Menachem Mendel Schneerson",
            "Rabbi Dovid Schoonmaker",
            "Rabbi Gedalya Schorr",
            "Rabbi Avroham Schorr",
            "Rabbi Nachman Schorr",
            "Rabbi Avraham Schorr",
            "Rabbi Avroham Schorr",
            "Rabbi Shimon Schwab",
            "Rabbi Yosef Chaim Schwab",
            "Rabbi Shalom Schwadron",
            "Rabbi Yitzchak Schwartz",
            "Rabbi Zecharia Schwartz",
            "Rabbi Zevulun Schwartzman",
            "Rabbi Peretz Segal",
            "Rabbi Don Segal",
            "Rabbi Yehuda Zev Segal",
            "Rabbi Shraga Senft",
            "Rabbi Elazar Menachem Shach",
            "Rabbi Bentzion Shafier",
            "Rabbi Shmuel Shapira",
            "Rabbi Moshe Shapira",
            "Rabbi Yaakov Shapiro",
            "Rabbi Yehuda Leib Shapiro",
            "Rabbi Ephraim Eliyahu Shapiro",
            "Rabbi Hillel Shaps",
            "Rabbi Moshe Sherrow",
            "Rabbi Eliezer Sherwinter",
            "Rabbi Chaim Shmuelevitz",
            "Rabbi Benayahu Shmueli",
            "Rabbi Ariel Shoshan",
            "Rabbi Ahron Leib Shteinman",
            "Rabbi Moshe Aharon Shuchatowitz",
            "Rabbi Mordechai Shuchatowitz",
            "Rabbi Eliezer Shulman",
            "Rabbi Shmaryahu Shulman",
            "Rabbi Yaakov Moishe Shurkin",
            "Rabbi Fyvel Shuster",
            "Rabbi Pesach Siegel",
            "Rabbi Shmuel Silber",
            "Rabbi Elimelech Silberberg",
            "Rabbi Shmuel Silinsky",
            "Rabbi Dave Silverman",
            "Rabbi Tovia Singer",
            "Rabbi Yosef Singer",
            "Rabbi Mordechai Sitorsky",
            "Rabbi Shmuel Skaist",
            "Rabbi Michael Skobac",
            "Rabbi Michoel Slavin",
            "Rabbi Binyomin Sloviter",
            "Rabbi Yackov Zev Smith",
            "Rabbi Aryeh Sokoloff",
            "Rabbi Yosef Dov Soloveitchik",
            "Rabbi Yosef Sonnenschein",
            "Rabbi Yitzchok Sorotzkin",
            "Rabbi Aharon Sorscher",
            "Rabbi Aharon Sorscher",
            "Rabbi Yechiel Spira",
            "Rabbi Ken Spiro",
            "Rabbi Chaim Dov Stark",
            "Rabbi Chaim Stein",
            "Rabbi Reuven Stein",
            "Rabbi Moshe Steinberg",
            "Rabbi Avraham Steinberg",
            "Rabbi Adin Steinsaltz",
            "Rabbi Reuven Stepsky",
            "Rabbi Simcha Stern",
            "Rabbi Ari Storch",
            "Rabbi Hillel Strauss",
            "Rabbi Elya Svei",
            "Rabbi Mordechai Swimmer",
            "Rabbi Ephraim Tanenbaum",
            "Rabbi Akiva Tatz",
            "Rabbi Ezriel Tauber",
            "Rabbi Michael Taubes",
            "Rabbi Zvi Teitelbaum",
            "Rabbi Hanoch Teller",
            "Rabbi Yosef Tendler",
            "Rabbi Yitzchok Tendler",
            "Rabbi Tzvi Thaler",
            "Rabbi Meir Triebitz",
            "Rabbi Mayer Turin",
            "Rabbi Meir Baruch Turin",
            "Rabbi Efraim Twerski",
            "Rabbi Yosef Viener",
            "Rabbi Yehuda Vosner",
            "Rabbi Ephraim Wachsman",
            "Rabbi Ari Walden",
            "Rabbi Zecharia Wallerstein",
            "Rabbi Moshe Walter",
            "Rabbi Berel Wein",
            "Rabbi Mendel Weinbach",
            "Rabbi Noah Weinberg",
            "Rabbi Yaakov Weinberg",
            "Rabbi Brahm Weinberg",
            "Rabbi Dovid Weinberg",
            "Rabbi Moshe Weinberger",
            "Rabbi Yitzi Weiner",
            "Rabbi Tzvi Hersh Weinreb",
            "Rabbi Eliyahu Weintraub",
            "Rabbi Dov Ber Weisman",
            "Rabbi Asher Weiss",
            "Rabbi Yosef Wikler",
            "Reb Dr Meir Wikler",
            "Rabbi Mordechai Willig",
            "Rabbi Menachem Winter",
            "Rabbi Kalman Winter",
            "Rabbi Shlomo Wolbe",
            "Rabbi Moshe Wolfson",
            "Rabbi Berel Wolvovsky",
            "Rabbi Shmuel Wosner",
            "Rabbi Asher Yablok",
            "Rabbi Avrohom Yachnes",
            "Rabbi Menacheim Yakbzon",
            "Rabbi Shmuel Yeshaya Yoffe",
            "Rabbi Ovadia Yosef",
            "Rabbi Benjamin Yudin",
            "Rabbi Menashe Zadka",
            "Rabbi Yonatan Zakem",
            "Rabbi Avi Zakutinsky",
            "Rabbi Michel Zilber",
            "Rabbi Tzvi Meir Zilberberg",
            "Rabbi Yitzchok Zilberstein",
            "Rabbi Zvi Zimmerman",
            "Rabbi Elchonon Zohn",
            "Rabbi Tal Moshe Zwecker",
            "Rabbi Yochanan Zweig",
        )
            .sortedBy {
                it.substring(it.indexOf(" ") + 1).first()
                    .toUpperCase()
                    .toString() //it is sorted this way for the entries like "Reb Dovid Hillel", etc. which end up at the end because "Reb" is later than "Rab"
            }
        listOfAvailableCategories = listOf(
            "Sifrei Kodesh",
            "Chazal",
            "Pirkei Avos",
            "Pirkei Drabbi Eliezer",
            "Rishonim",
            "Chovos Halvovos",
            "Igeres HaRamban",
            "Kuzari",
            "Sefer HaChinuch",
            "Shaarei Kedushah",
            "Shaarei Teshuvah",
            "Rambam",
            "Sefer Nezikin",
            "Geneiva",
            "Nizkei Momoin",
            "Moreh Nevuchim",
            "Sefer Madah",
            "Hilchos Avodas Kochavim",
            "Hilchos Yesodei Hatorah",
            "Hilchos Deyos",
            "Hilchos Talmid Torah",
            "Hilchos Teshuvah",
            "Sefer Ahavah",
            "Hilchos Seder Tefillah",
            "Hilchos Krias Shema",
            "Hilchos Tzitzis",
            "Hilchos Milah",
            "Sefer Zmanim",
            "Hilchos Kiddush HaChodesh",
            "Hilchos Megillah Chanukah",
            "Hilchos Chomeitz uMatzah",
            "Hilchos Shkalim",
            "Hilchos Shevisas Asor",
            "Hilchos Shevisas Yom Tov",
            "Hilchos Eruvin",
            "Sefer Nashim",
            "Hilchos Ishus",
            "Hilchos Get",
            "Hilchos Yibum and Chalitzah",
            "Hilchos Naaroh Besulah",
            "Hilchos Sotah",
            "Sefer Kedusha",
            "Hilchos Issurei Biah",
            "Hilchos Shechitah",
            "Sefer Haflaah",
            "Hilchos Shevuos",
            "Hilchos Nedorim",
            "Hilchos Neziros",
            "Yud Gimmel Ikarim",
            "Perek Chelek",
            "Shmona Prakim",
            "Igros HaRambam",
            "Igeres Hateshuva",
            "Sefer HaIkkarim",
            "Archos Tzaddikim",
            "Shaarei HaAvodah",
            "Acharonim",
            "Nefesh Hachayim",
            "Igeres HaMussar",
            "Ruach Chaim",
            "Reb Tzadok Hakohen",
            "Tomer Devorah",
            "Maharal",
            "Tiferes Yisroel",
            "Maharal Hagadah",
            "Gevuros Hashem",
            "Derech Chaim",
            "Nesiv Koach HaYetzer",
            "Nesiv Ahavas Reya",
            "Nesiv HaTorah",
            "Nesiv HaZrizus",
            "Nesiv HaTznius",
            "Nesiv HaPrishus",
            "Nesiv HaOsher",
            "Nesiv HaKaas",
            "Nesiv HaEmuna",
            "Nesiv HaBitachon",
            "Nesiv HaAvodah",
            "Nesiv HaShtika",
            "Nesiv HaYissurin",
            "Nesiv HaTeshuva",
            "Ohr Chadash",
            "Ramchal",
            "Daas Tevunos ",
            "Derech Hashem",
            "Mesilas Yesharim",
            "Yesod VeShoresh HaAvoda",
            "Teshuvos Rebbe Akiva Eiger",
            "Nesivos Shalom",
            "Ben Ish Chai",
            "Tanya",
            "Contemporary",
            "Alei Shur",
            "Mishnah Berurah",
            "Yesodei HaTorah",
            "Igros Chazon Ish",
            "Chaye Adam",
            "Chochmas Adam",
        ).sorted()
        listOfAvailableSeries = listOf("Amud Yomi", "Daf Yomi", "a")
        //</editor-fold>

        setAdapterAndSetHints(
            filterCriterionChooser,
            listOfFilterCriterion
        ) //TODO somehow also change the hint for individualSpeakerCategorySeriesTextInputLayout (hence "...Hint*s*(...)". Possibly create a Map<speakerCategorySeries :String, listOfAvailable... :List<String>>

//<editor-fold listeners>
        progressiveFilterExplanationImageButton.setOnClickListener {
            val alertDialog: AlertDialog? =
                context?.let { it1 -> AlertDialog.Builder(it1).create() }
            alertDialog?.setTitle("Progressive Filtering")
            alertDialog?.setMessage("")
            alertDialog?.setButton(
                AlertDialog.BUTTON_NEUTRAL, "OK"
            ) { dialog, _ -> dialog.dismiss() }
            alertDialog?.setCanceledOnTouchOutside(true)
            alertDialog?.show()
        }
        filterRadioButton.setOnClickListener {

            filterButton.text = "Filter"
            //sort order inapplicable by filtering, so hide sort order dropdown
            sortOrderDropdown.visibility = View.GONE
            sortOrderTextInputLayout.visibility = View.GONE
        }
        sortRadioButton.setOnClickListener {

            filterButton.text = "Sort"
            sortOrderDropdown.visibility = View.VISIBLE
            sortOrderTextInputLayout.visibility = View.VISIBLE

        }
        filterCriterionChooser.setOnItemClickListener { _, _, position, _ ->
            /*  inlined for efficiency
            val speakerString = resources.getString(TabType.SPEAKER.nameId)
            val categoryString = resources.getString(TabType.CATEGORY.nameId)
            val seriesString = resources.getString(TabType.SERIES.nameId)*/
            val (appropriateList, tabType) = when (position) {
                0 -> listOf(
                    listOfAvailableSpeakers,
                    resources.getString(TabType.SPEAKER.nameId)
                ) //calls to getString() are good for DRY and localization
                1 -> listOf(listOfAvailableCategories, resources.getString(TabType.CATEGORY.nameId))
                2 -> listOf(listOfAvailableSeries, resources.getString(TabType.SERIES.nameId))
                else -> listOf(listOf<String>(), "")
            }
            tabTypeBeingDisplayed = TabType.valueOf((tabType as String).toUpperCase(Locale.ROOT))
            if(selectedListItem!="") individualSpeakerCategorySeriesChooserAutoCompleteTextView.setText("")
            individualSpeakerCategorySeriesChooserAutoCompleteTextView.hint = "$tabType..."
            fastScrollerDialogListItems = (appropriateList as List<String>)
            //TODO populate speaker/category/series popup with appropriate data
        }
        val populateAndDisplayFastScrollerDialogLambda = { _: View ->
            Log.d(TAG, "individualSpeakerCategorySeriesChooser clicked")

/*
            val randomWord = { length:Int ->
                val allowedChars = ('A'..'Z') + ('a'..'z')
                (1..length)
                    .map { allowedChars.random() }
                    .joinToString("")
            }
            val randomListOfWords = mutableListOf<String>()
            for (i in 1..500) randomListOfWords.add(randomWord(5))


             fastScrollerDialogListItems = randomListOfWords.sorted()*/
            if (::tabTypeBeingDisplayed.isInitialized || ::fastScrollerDialogListItems.isInitialized) ChooserFastScrollerDialog( // would use &&, but if one is true, both are true, and && will always check both, but || will only check the second if the first is false, and I think that if the first is false, the second must also be false, but I am not 100% sure.
                tabTypeBeingDisplayed,
                fastScrollerDialogListItems
            ).show(
                childFragmentManager,
                TAG
            )
        }
//        individualSpeakerCategorySeriesChooser.setOnClickListener()
        individualSpeakerCategorySeriesChooserAutoCompleteTextView.setOnClickListener(
            populateAndDisplayFastScrollerDialogLambda
        )
        individualSpeakerCategorySeriesChooser.setEndIconOnClickListener(
            populateAndDisplayFastScrollerDialogLambda
        )  // so that the user is not confused when clicking the end icon doesn't display the dialog, but the main body of the OutlinedBox does display it.
        filterButton.setOnClickListener {
            //send back data to PARENT fragment using callback
            callbackListener.onDataReceived(tabTypeBeingDisplayed, selectedListItem)
            // Now dismiss the fragment
            dismiss()
        }
        cancelButton.setOnClickListener {
            // Dismiss the fragment
            dismiss()
        }

        filterRadioButton.performClick() //Indicate that dialog default is filtering
        //</editor-fold>
    }

    //<editor-fold set hint and adapter functions>
    private fun setAdapterAndSetHints(
        viewResId: Int,
        filterCondition: List<String>,
        view: View
    ) {
        val alphabeticalAdapter =
            getDropdownAdapter(filterCondition)
        val secondCondition = filterCondition[1]
        val dropDownMenu: AutoCompleteTextView =
            view.findViewById(viewResId)
        dropDownMenu.setText(secondCondition, false)
        individualSpeakerCategorySeriesChooserAutoCompleteTextView.hint = secondCondition
        dropDownMenu.setAdapter(alphabeticalAdapter)
    }

    private fun setAdapterAndSetHints(
        dropDownMenu: AutoCompleteTextView,
        filterCondition: List<String>
    ) {
        val alphabeticalAdapter =
            getDropdownAdapter(filterCondition)
        val firstCondition = filterCondition[0]
        dropDownMenu.setText(firstCondition, false)

        individualSpeakerCategorySeriesChooserAutoCompleteTextView.hint = "$firstCondition..."
        tabTypeBeingDisplayed = TabType.valueOf(firstCondition.toUpperCase(Locale.ROOT))
        fastScrollerDialogListItems = listOfPossibleListItems[0]
        dropDownMenu.setAdapter(alphabeticalAdapter)
    }
//</editor-fold>

    private fun getDropdownAdapter(filterCondition: List<String>): ArrayAdapter<String>? {
        return context?.let {
            ArrayAdapter<String>(
                it,
                R.layout.sort_or_filter_dropdown_menu_item,
                filterCondition
            )
        }
    }
}

/*  //setup for advanced_sort_or_filter_dialog
       val seriesCheckbox = view.findViewById<MaterialCheckBox>(R.id.series_checkbox)
       val andOrDropdown = listOf("OR","AND")
       val speakerDropdown = listOf("Rabbi Gedaliah Anemer", "Rabbi Aaron Lopiansky", "a")
       val categoryDropdown = listOf("Kil'ayim", "Mikvaos", "a")
       val seriesOrder = listOf("Daf Yomi", "Amud Yomi", "a")
       val alphabeticalOrder = listOf("Descending", "Ascending", "a")

       setAdapterAndDefault(R.id.speaker_dropdown, speakerDropdown, view)
       setAdapterAndDefault(R.id.category_dropdown, categoryDropdown, view)
       setAdapterAndDefault(R.id.series_dropdown, seriesOrder, view)

       setAdapterAndDefault(andOrDropdown, view, R.id.speaker_and_or_dropdown)
       setAdapterAndDefault(R.id.category_and_or_dropdown, andOrDropdown, view)
       setAdapterAndDefault(R.id.series_and_or_dropdown, andOrDropdown, view)
       setAdapterAndDefault(R.id.alphabetical_order_dropdown, alphabeticalOrder, view)


       val viewTreeObserver = view.viewTreeObserver
       if (viewTreeObserver.isAlive) {
           viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
               override fun onGlobalLayout() {
                   view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                   Log.d(
                       "FilterDialog",
                       "Category text input height: " + view.findViewById<TextInputLayout>(R.id.category_text_input_layout).height
                   )
                   Log.d(
                       "FilterDialog",
                       "Category dropdown height: " + view.findViewById<AutoCompleteTextView>(R.id.category_dropdown).height
                   )
// TODO refactor setting default radio button (is it possible to do in code? no call to check() seems to work, even in tree observer,  and respective click listeners and setText() (for filter/sort button at bottom) to function to modular/make easy to change                   filterSortRadioGroup.check(R.id.sort_radio_button) //Indicate that dialog default setting is filtering, to match with XML title attr (had to set something as default)
   answer: figured out that you can call callOnClick()
               }
           })
       }

       seriesCheckbox.setOnCheckedChangeListener{ compoundButton, boolean ->
           when (boolean){
               //TODO put this when inside of the check() call
               true -> filterSortRadioGroup.check(R.id.sort_radio_button)
               false ->filterSortRadioGroup.check(R.id.filter_radio_button)
           }
       }
*/

class ChooserFastScrollerDialog(val mode: TabType, private val listItems: List<String>) :
    DialogFragment() {
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var toolbar: Toolbar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = true
        setHasOptionsMenu(true)
        val view: View =
            inflater.inflate(R.layout.fast_scroll_recycler_dialog_layout, container, false)
        selectedListItemTextView = view.findViewById(R.id.selected_list_item)
        toolbar = view.findViewById(R.id.custom_dialog_layout_toolbar)
        fastScrollerCancelButton = view.findViewById(R.id.fast_scroller_cancel_button)
        fastScrollerDeselectButton = view.findViewById(R.id.fast_scroller_deselect_button)
        fastScrollerSelectButton = view.findViewById(R.id.fast_scroller_select_button)

        toolbar.inflateMenu(R.menu.speaker_page_menu)
        toolbar.title = resources.getString(tabTypeBeingDisplayed.nameId)

        val menu: Menu = toolbar.menu
        //<editor-fold desc="SearchView decleration">
        val searchView = menu.findItem(R.id.actionSearch).actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                itemAdapter.filter(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "Search text changed.")
                itemAdapter.filter(newText ?: "")
                return false
            }
        })
        //</editor-fold>
        fastScrollerCancelButton.setOnClickListener {
            dismiss()
        }
        fastScrollerSelectButton.setOnClickListener {
            individualSpeakerCategorySeriesChooserAutoCompleteTextView.setText(selectedListItem)
            dismiss()
        }
        fastScrollerDeselectButton.setOnClickListener {
            selectedListItemTextView.text = ""
            selectedListItem = ""
            fastScrollerDeselectButton.isEnabled = false
            fastScrollerSelectButton.isEnabled = false
        }
        return view
    }

    override fun getTheme(): Int = R.style.DialogTheme


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: FastScrollView? = view.findViewById(R.id.fast_scroller)
        recyclerView?.setLayoutManager(LinearLayoutManager(context))

        fastScrollerCancelButton = view.findViewById(R.id.fast_scroller_cancel_button)
        fastScrollerDeselectButton = view.findViewById(R.id.fast_scroller_deselect_button)
        fastScrollerSelectButton = view.findViewById(R.id.fast_scroller_select_button)

        itemAdapter = ItemAdapter(listItems)
        recyclerView?.setAdapter(itemAdapter)

    }

    class ItemAdapter(private val listItems: List<String>) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>(), FastScroller.SectionIndexer {
        val tempListItems = listItems.toMutableList()

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: MaterialTextView = view.findViewById(R.id.text_view)

            init {

                view.setOnClickListener {

                    Log.d("", "Element $adapterPosition clicked.")
                    fastScrollerDeselectButton.isEnabled = true
                    fastScrollerSelectButton.isEnabled = true
                    tempListItems[adapterPosition].let {
                        selectedListItemTextView.text = it
                            selectedListItem = it
                    }
                }
            }
        }

        override fun getSectionText(position: Int): CharSequence  {
            val s = tempListItems[position]
            return if (s.contains("Rabbi"))
                s.substring(s.indexOf(" ") + 1).first().toUpperCase().toString()
            else s.first().toUpperCase().toString()
        }


        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.simple_grey_text_view, viewGroup, false)
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            Log.d("", "Element $position set.")

            viewHolder.textView.text = tempListItems[position]
        }

        override fun getItemCount(): Int = tempListItems.size
        fun filter(constraint: String) {
            Log.d(TAG, "Filter ran")
/*
        var completeListIndex = 0
        var filteredListIndex = 0
        while (completeListIndex < originalSpeakerList.size) {
            val speaker: Speaker = originalSpeakerList[completeListIndex]
            if (speaker.name.toLowerCase(Locale.ROOT).trim().contains(constraint)) {
                if (filteredListIndex < speakerList.size) {
                    val filter: Speaker = speakerList[filteredListIndex]
                    if (speaker.name != filter.name) {
                        speakerList.add(filteredListIndex, speaker)
                        notifyItemInserted(filteredListIndex)
                    }
                } else {
                    speakerList.add(filteredListIndex, speaker)
                    notifyItemInserted(filteredListIndex)
                }
                filteredListIndex++
            } else if (filteredListIndex < speakerList.size) {
                val filter: Speaker = speakerList[filteredListIndex]
                if (speaker.name==filter.name) {
                    speakerList.removeAt(filteredListIndex)
                    notifyItemRemoved(filteredListIndex)
                }
            }
            completeListIndex++
        }
*/
            tempListItems.clear()
            if (constraint.isEmpty()) {
                tempListItems.addAll(listItems)
            } else {
                val filterPattern = constraint.toLowerCase(Locale.ROOT).trim()
                for (listItem in listItems) {
                    if (listItem.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                        tempListItems.add(listItem)
                    }
                }
            }
            notifyDataSetChanged()
        }

    }
}
