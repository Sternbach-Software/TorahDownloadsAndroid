package tech.torah.aldis.androidapp.dataClassesAndInterfaces

import android.os.Parcelable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.ShiurCategoryPage
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.ShiurFullPage

//[Tt]orah(?!\.?(anytime|torahanytime))
object CONSTANTS {
    val genericListOfAvailableSpeakers = listOf(
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
    val genericListOfAvailableCategories = listOf(
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
    val genericListOfAvailableSeries = listOf("Amud Yomi", "Daf Yomi", "a")
    val serverUrl = "https://www.torahdownloads.org"
    val sampleListOfShiurim = arrayListOf<Parcelable>(
        ShiurFullPage(
        title = "Halachos of Neir Havdala; Emulating HASHEM's Chesed  (Agudah Parsha Shiur Beraishis  5777)",
        speaker = "Rabbi Yehuda Ades",
        category = "Chayei Sarah"
        ),
        ShiurFullPage(
            title = "Halachos of Neir Havdala; Emulating HASHEM's Chesed  (Agudah Parsha Shiur Beraishis  5777)",
            speaker = "Rabbi Gedaliah Anemer",
            category = "Chayei Sarah"
        ),
        ShiurFullPage(
            title = "Languages- Parshas Beraishis 5779",
            speaker = "Rabbi David Ashear",
            category = "Chayei Sarah"
        ),
        ShiurFullPage(
            title = "Languages- Parshas Beraishis 5779",
            speaker = "Rabbi Baruch Shalom HaLevi Ashlag",
            category = "Chayei Sarah"
        ),
        ShiurFullPage(
            title = "Parshas B01 Beraishis 5776",
            speaker = "Rabbi Shmuel Auerbach",
            category = "Chayei Sarah"
        ),
        ShiurFullPage(
            title = "Parshas B01 Beraishis 5779",
            speaker = "Rabbi Elkanah Austern",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Parshas B01 Beraishis 5780",
            speaker = "Rabbi Stephen Baars",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Parshas B01 Bereshis 5773",
            speaker = "Rabbi Shmuel Eliezer Baddiel",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Why We left Gan Eden; Teshuva is Now, and Defining Ourselves  (Parshas Beraishis 5777)",
            speaker = "Rabbi Yudi Bakst",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Why We left Gan Eden; Teshuva is Now, and Defining Ourselves  (Parshas Beraishis 5777)",
            speaker = "Rabbi Chaim Balter",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Yom Hashishi; Purpose and Function- Parshas Beraishis 5780",
            speaker = "Rabbi Yitzchok Basser",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Yom Hashishi; Purpose and Function- Parshas Beraishis 5780.MP3",
            speaker = "Rabbi David Ashear",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Bracha on the Keshes and Thunder and Lightning  (Agudah Parsha Shiur Noach 5779)",
            speaker = "Rabbi Baruch Shalom HaLevi Ashlag",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Bracha on the Keshes and Thunder and Lightning  (Agudah Parsha Shiur Noach 5779)",
            speaker = "Rabbi Shmuel Auerbach",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Chassidus and Tzidkus; Ahavah and Yirah- Parshas Noach 5779",
            speaker = "Rabbi Elkanah Austern",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Chassidus and Tzidkus; Ahavah and Yirah- Parshas Noach 5779",
            speaker = "Rabbi Stephen Baars",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Dor Haflagah and History of the World Until Our Generation; 5 Explanations (Parshas Noach 5777)",
            speaker = "Rabbi Shmuel Eliezer Baddiel",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Dor Haflagah and History of the World Until Our Generation; 5 Explanations (Parshas Noach 5777)",
            speaker = "Rabbi Yudi Bakst",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Nation-States and Checks and Balances; Seeing Humanity in Others- Parshas Noach 5780",
            speaker = "Rabbi Chaim Balter",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Nation-States and Checks and Balances; Seeing Humanity in Others- Parshas Noach 5780",
            speaker = "Rabbi Yitzchok Basser",
            category = "Toldos"
        ),
        ShiurFullPage(
            title = "Parshas B02 Noach (2) 5779",
            speaker = "Rabbi David Ashear",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Parshas B02 Noach 5773",
            speaker = "Rabbi Baruch Shalom HaLevi Ashlag",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Parshas B02 Noach 5775",
            speaker = "Rabbi Shmuel Auerbach",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Parshas B02 Noach 5778",
            speaker = "Rabbi Elkanah Austern",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Parshas B02 Noach 5779",
            speaker = "Rabbi Stephen Baars",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Parshas B02 Noach 5780",
            speaker = "Rabbi Shmuel Eliezer Baddiel",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Parshas Noach 5777",
            speaker = "Rabbi Yudi Bakst",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Vegetarianism-Parshas Noach 5778",
            speaker = "Rabbi Chaim Balter",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Vegetarianism-Parshas Noach 5778",
            speaker = "Rabbi Yitzchok Basser",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Vegetarianism; Halacha and Hashkafa (Agudah Parsha Shiur Parshas Noach 5778)",
            speaker = "Rabbi David Ashear",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Vegetarianism; Halacha and Hashkafa (Agudah Parsha Shiur Parshas Noach 5778)",
            speaker = "Rabbi Baruch Shalom HaLevi Ashlag",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Bris and Os Bris- Parshas Lech Lecha 5779",
            speaker = "Rabbi Shmuel Auerbach",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Bris and Os Bris- Parshas Lech Lecha 5779",
            speaker = "Rabbi Elkanah Austern",
            category = "Vayeitzei"
        ),
        ShiurFullPage(
            title = "Parshas B03 Lech Lecha 5773",
            speaker = "Rabbi Stephen Baars",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Parshas B03 Lech Lecha 5774",
            speaker = "Rabbi Shmuel Eliezer Baddiel",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Parshas B03 Lech Lecha 5775",
            speaker = "Rabbi Yudi Bakst",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Parshas B03 Lech Lecha 5776",
            speaker = "Rabbi Chaim Balter",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Parshas B03 Lech Lecha 5778",
            speaker = "Rabbi Yitzchok Basser",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Parshas B03 Lech Lecha 5779",
            speaker = "Rabbi David Ashear",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Parshas B03 Lech Lecha 5780",
            speaker = "Rabbi Baruch Shalom HaLevi Ashlag",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Self Esteem and Emunah- Parshas Lech Lecha 5780",
            speaker = "Rabbi Shmuel Auerbach",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Simcha and Schok-Parshas Lech Lecha 5778",
            speaker = "Rabbi Elkanah Austern",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Simcha and Schok-Parshas Lech Lecha 5778",
            speaker = "Rabbi Stephen Baars",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Bikur Cholim; Halacha and Hashkafa (Agudah Parsha Shiur Parshas Vayera 5778)",
            speaker = "Rabbi Shmuel Eliezer Baddiel",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Hachnasas Orchim (Agudah Parsha Shiur Vayera 5777)",
            speaker = "Rabbi Yudi Bakst",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Hachnasas Orchim (Agudah Parsha Shiur Vayera 5777)",
            speaker = "Rabbi Chaim Balter",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Kiddush Hashem and Chillul Hashem- Parshas Vayeira 5779",
            speaker = "Rabbi Yitzchok Basser",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Kiddush Hashem and Chillul Hashem- Parshas Vayeira 5779",
            speaker = "Rabbi Mordechai Becher",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Parshas  Vayera 5777",
            speaker = "Rabbi Yosef Bechhofer",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Parshas B04 Vayeira 5773",
            speaker = "Rabbi Ian Beider",
            category = "Vayishlach"
        ),
        ShiurFullPage(
            title = "Parshas B04 VaYeira 5774",
            speaker = "Rabbi Berel Bell",
            category = "Vayeshev"
        ),
        ShiurFullPage(
            title = "Parshas B04 Vayera 5775",
            speaker = "Rabbi Yisroel Belsky",
            category = "Vayeshev"
        ),
        ShiurFullPage(
            title = "Parshas B04 Vayera 5778",
            speaker = "Rabbi Yaakov Bender",
            category = "Vayeshev"
        ),
        ShiurFullPage(
            title = "Parshas B04 Vayera 5780",
            speaker = "Rabbi Yosef Berger",
            category = "Vayeshev"
        ),
        ShiurFullPage(
            title = "Sederes Bnai Torah 01-Shimush Talmidei Chachamim(Vayera 5773)",
            speaker = "Rabbi Motty Berger",
            category = "Vayeshev"
        ),
        ShiurFullPage(
            title = "Mishnah Berurah Siman 352 Hakorei b'Sefer v'Nisgalgal meReshus l'reshus Seif 2; Siman 353 Dinei Zizin b'Reshus HaRabim Seif 1-2",
            speaker = "Rabbi Michael Berger"
        ),
        ShiurFullPage(
            title = "Mishnah Berurah Siman 353 Dinei Zizin b'Reshus HaRabim Seif 2-3; Siman 354 Dinei Bor v'Ashpa BeReshus Harabim Seif 1",
            speaker = "Rabbi Moshe Bergman"
        ),
        ShiurFullPage(
            title = "Mishnah Berurah Siman Siman 310 Dinei Muktzeh Seif 7 5778",
            speaker = "Rabbi Yitzchak Berkovits"
        ),
        ShiurFullPage(
            title = "Mishnah Berurah Siman Siman 310 Dinei Muktzeh Seif 7-9",
            speaker = "Rabbi Tzvi Berkowitz"
        ),
        ShiurFullPage(
            title = "Mishnah Berurah Siman Siman 311 Dinei Mais and Tiltul Min Hatzad Seif 1-2",
            speaker = "Rabbi Yitzchak Berkowitz"
        ),
    )
    val regexForTitle = """(?<="Title": ")[\w\s-()\.'-,;]+(?=",)"""
    val randomWord = { wordLength:Int ->
        val allowedChars = ('A'..'Z') + ('a'..'z')
        (1..wordLength)
            .map { allowedChars.random() }
            .joinToString("")
    }
    val listOfRandomWords = {numWords:Int, wordLength:Int->
        val randomListOfWords = mutableListOf<String>()
        for (i in 1..numWords) randomListOfWords.add(randomWord(wordLength))
    }

    /**
     * An ArrayList<String> of speaker details
     * @size 2 elements
     * @element First is the speaker name
     * @element Second is the description
     * */
    val INTENT_EXTRA_SPEAKER_DETAILS = "tech.torah.aldis.android.speakers.details"

    /**
     * An ArrayList<Category> of category details.
     * @size 4 elements
     * @element First is the category name
     * @element Second is a subcategory
     * @element Third is a subcategory
     * @element Fourth is a subcategory
     * */
    val INTENT_EXTRA_CATEGORY_DETAILS = "tech.torah.aldis.android.category.details"
    val INTENT_EXTRA_SUBCATEGORY_PARENT_NAME = "tech.torah.aldis.android.category.parent_name"
    val INTENT_EXTRA_CATEGORY_CHILD_SHIURIM = "tech.torah.aldis.android.category.shiurim"
    val INTENT_EXTRA_SPEAKER_CHILD_SHIURIM = "tech.torah.aldis.android.speaker.shiurim"
    val INTENT_EXTRA_MORE_FROM_THIS_SPEAKER = "tech.torah.aldis.android.more_for_this.speaker"
    val INTENT_EXTRA_MORE_FROM_THIS_CATEGORY = "tech.torah.aldis.android.more_for_this.category"
    val INTENT_EXTRA_MORE_FROM_THIS_SERIES = "tech.torah.aldis.android.more_for_this.series"
    val INTENT_EXTRA_SHIURIM_PAGE_TITLE = "tech.torah.aldis.android.shiurim_page.title"
    val INTENT_EXTRA_SHIURIM_PAGE_SHIURIM = "tech.torah.aldis.android.shiurim_page.shiurim"

}