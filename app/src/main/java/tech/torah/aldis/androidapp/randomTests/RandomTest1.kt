package tech.torah.aldis.androidapp.randomTests

import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Shiur
import java.io.File

//import tech.torah.aldis.androidapp.Constants

/*
import android.util.Log
import tech.torah.aldis.androidapp.Constants
import tech.torah.aldis.androidapp.dataClasses.Speaker
import java.io.File
import java.text.DecimalFormat
*/


fun main() {
/*    val listOfNameLengths = mutableListOf<Int>()
    val regex = "(?<=\"name\":\")[\\w\\s]+(?=\")".toRegex()
    var max = 0
        regex.findAll(File("listOfSpeakers.json").readText()).forEach{
        if(it.value.length>max) {max = it.value.length}
    }
    println(max)*/
    var linesOfCode = 0
        File("C:\\Users\\shmue\\AndroidStudioProjects\\vlc-android\\application\\vlc-android\\src").walk()
            .toList().filter { it.extension == "kt" }.forEach {
                linesOfCode += it.readLines().size
            }
    println(linesOfCode)

/*
    File("C:\\Users\\shmue\\AndroidStudioProjects\\TorahDownloadsAndroid\\app\\src\\main\\java\\tech\\torah\\aldis\\androidapp").walk()
        .toList()
        .filter { it.extension == "kt" }.fold(0) { acc, file -> acc + file.readLines().size }
        .println()


    File("C:\\Users\\shmue\\AndroidStudioProjects\\TorahDownloadsAndroid\\app\\src\\main\\java\\tech\\torah\\aldis\\androidapp")
        .walk()
        .toList()
        .filter {
            it
                .extension == "kt"
        }.myforEach {

        }.println()
    val x = mutableListOf<Shiur>()
    listOf(Shiur(), Shiur()).filter { it.description.first() == 't' }.myforEach {
        if (it.id.toInt() > 500) {x.add(it)}
    }.println()*/
}

private fun Int.println() = println(this)


public inline fun <T> List<T>.myforEach(action: (T) -> Unit): List<T> {
    for (element in this) action(element)
    return this
}
fun addToXIfIDisGreaterThanFIveHundred(it: Shiur, x: MutableList<Shiur>) {

}


fun <T> List<T>.println() = println(this)

public inline fun <T> Sequence<T>.myforEach(action: (T) -> Unit): Sequence<T> {
    for (element in this) action(element)
    return this
}
/*
fun filter(query: String?) {
    var completeListIndex = 0
    var filteredListIndex = 0
    while (completeListIndex < completeList.size()) {
        val item: Movie = completeList.get(completeListIndex)
        if (item.getName().toLowerCase().contains(query)) {
            if (filteredListIndex < filteredList.size()) {
                val filter: Movie = filteredList.get(filteredListIndex)
                if (!item.getName().equals(filter.getName())) {
                    filteredList.add(filteredListIndex, item)
                    notifyItemInserted(filteredListIndex)
                }
            } else {
                filteredList.add(filteredListIndex, item)
                notifyItemInserted(filteredListIndex)
            }
            filteredListIndex++
        } else if (filteredListIndex < filteredList.size()) {
            val filter: Movie = filteredList.get(filteredListIndex)
            if (item.getName().equals(filter.getName())) {
                filteredList.remove(filteredListIndex)
                notifyItemRemoved(filteredListIndex)
            }
        }
        completeListIndex++
    }
}*/
