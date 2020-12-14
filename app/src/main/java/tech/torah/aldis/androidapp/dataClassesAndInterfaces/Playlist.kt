package tech.torah.aldis.androidapp.dataClassesAndInterfaces

data class Playlist(
    val playlistName: String = "Playlist name",
    val totalNumberOfShiurim:Int = 3,
    val numberOfCompletedShiurim: Int = 1,
    val listOfShiurim:List<ShiurFullPage> = listOf(ShiurFullPage(),ShiurFullPage(), ShiurFullPage()),
    val totalLengthInSeconds:Int = 578,
    val numberOfSpeakers: Int = 3
)
