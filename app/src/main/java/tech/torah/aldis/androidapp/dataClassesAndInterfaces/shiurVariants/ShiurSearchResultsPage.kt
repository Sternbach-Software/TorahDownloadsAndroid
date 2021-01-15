package tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants

data class ShiurSearchResultsPage(
val ShiurID:String= "20373",
val Title:String= "#06 B'Sugya D'Ner Chanukah Al Kol Pesach Vepesach",
val Length:String= "1732",
val FormattedLength:String= "00:28:52",
val SpeakerID:String= "140",
val Speaker:String= "Rabbi Yaakov Moishe Shurkin",
val SpeakerSE:String= "rabbi- yaakov-moishe-shurkin",
val url:String= "\\/shiur-20373.html"
):Shiur(ShiurID,Title,Length,Speaker)
