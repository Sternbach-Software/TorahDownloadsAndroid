package tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants

data class ShiurQuickSeriesShiurPage(
val ShiurID:String = "1023366",
val Series:String = "Daily Halacha - Dinei Bais Hakenesses",
val Title:String = "0206 - Dinei Bais Hakenesses - (Klal 17 Siman 06) - Kedushas Bais Hakenesses - 1 - Introduction_ Source for the Mitzvah",
val Length:String = "338",
val FormattedLength:String = "00:05:38",
val SpeakerID:String = "5",
val Speaker:String = "Rabbi Eliyahu Reingold",
val SpeakerSE:String = "rabbi-eliyahu-reingold"
):Shiur(ShiurID,Title,Length,Speaker)
