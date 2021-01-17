package tech.torah.aldis.androidapp.dataClassesAndInterfaces

import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur

data class Playlist(
    val playlistName: String = "Playlist name",
    val totalNumberOfShiurim:Int = 3,
    val numberOfCompletedShiurim: Int = 1,
    val listOfShiurim:List<Shiur> = listOf(
        Shiur(),
        Shiur(), Shiur()
    ),
    val totalLengthInSeconds:Int = 578,
    val numberOfSpeakers: Int = 3
)
