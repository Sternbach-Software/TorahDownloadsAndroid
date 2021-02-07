package tech.torah.aldis.androidapp.randomTests

import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.sort
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import java.util.*

//import tech.torah.aldis.androidapp.Constants

/*
import android.util.Log
import tech.torah.aldis.androidapp.Constants
import tech.torah.aldis.androidapp.dataClasses.Speaker
import java.io.File
import java.text.DecimalFormat
*/

@Suppress("Unused")
fun main() {
/*    val listOfNameLengths = mutableListOf<Int>()
    val regex = "(?<=\"name\":\")[\\w\\s]+(?=\")".toRegex()
    var max = 0
        regex.findAll(File("listOfSpeakers.json").readText()).forEach{
        if(it.value.length>max) {max = it.value.length}
    }
    println(max)*/
/*    var linesOfCode = 0
        File("C:\\Users\\shmue\\AndroidStudioProjects\\vlc-android\\application\\vlc-android\\src").walk()
            .toList().filter { it.extension == "kt" }.forEach {
                linesOfCode += it.readLines().size
            }
    println(linesOfCode)*/
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
    val x = mutableListOf<ShiurFullPage>()
    listOf(ShiurFullPage(), ShiurFullPage()).filter { it.description.first() == 't' }.myforEach {
        if (it.id.toInt() > 500) {x.add(it)}
    }.println()*/
/*    val whatTheListShouldBe: MutableList<Shiur> = mutableListOf(
        Shiur(baseSpeaker = "A", baseLength = "123", baseTitle = "a"),
        Shiur(baseSpeaker = "A", baseLength = "123", baseTitle = "b"),
        Shiur(baseSpeaker = "A", baseLength = "123", baseTitle = "c"),
        Shiur(baseSpeaker = "A", baseLength = "456", baseTitle = "d"),
        Shiur(baseSpeaker = "A", baseLength = "456", baseTitle = "e"),
        Shiur(baseSpeaker = "A", baseLength = "456", baseTitle = "f"),
        Shiur(baseSpeaker = "A", baseLength = "789", baseTitle = "g"),
        Shiur(baseSpeaker = "A", baseLength = "789", baseTitle = "h"),
        Shiur(baseSpeaker = "A", baseLength = "789", baseTitle = "i"),
        Shiur(baseSpeaker = "B", baseLength = "123", baseTitle = "j"),
        Shiur(baseSpeaker = "B", baseLength = "123", baseTitle = "k"),
        Shiur(baseSpeaker = "B", baseLength = "123", baseTitle = "l"),
        Shiur(baseSpeaker = "B", baseLength = "456", baseTitle = "m"),
        Shiur(baseSpeaker = "B", baseLength = "456", baseTitle = "n"),
        Shiur(baseSpeaker = "B", baseLength = "456", baseTitle = "o"),
        Shiur(baseSpeaker = "B", baseLength = "789", baseTitle = "p"),
        Shiur(baseSpeaker = "B", baseLength = "789", baseTitle = "q"),
        Shiur(baseSpeaker = "B", baseLength = "789", baseTitle = "r"),
        Shiur(baseSpeaker = "C", baseLength = "123", baseTitle = "s"),
        Shiur(baseSpeaker = "C", baseLength = "123", baseTitle = "t"),
        Shiur(baseSpeaker = "C", baseLength = "123", baseTitle = "u"),
    )
    val list: MutableList<Shiur> = mutableListOf(
        Shiur(baseSpeaker = "A", baseLength = "123", baseTitle = "a"),
        Shiur(baseSpeaker = "B", baseLength = "789", baseTitle = "r"),
        Shiur(baseSpeaker = "A", baseLength = "123", baseTitle = "b"),
        Shiur(baseSpeaker = "A", baseLength = "456", baseTitle = "d"),
        Shiur(baseSpeaker = "A", baseLength = "456", baseTitle = "e"),
        Shiur(baseSpeaker = "B", baseLength = "123", baseTitle = "k"),
        Shiur(baseSpeaker = "B", baseLength = "123", baseTitle = "j"),
        Shiur(baseSpeaker = "A", baseLength = "123", baseTitle = "c"),
        Shiur(baseSpeaker = "A", baseLength = "789", baseTitle = "h"),
        Shiur(baseSpeaker = "A", baseLength = "789", baseTitle = "i"),
        Shiur(baseSpeaker = "B", baseLength = "789", baseTitle = "p"),
        Shiur(baseSpeaker = "A", baseLength = "789", baseTitle = "g"),
        Shiur(baseSpeaker = "B", baseLength = "456", baseTitle = "m"),
        Shiur(baseSpeaker = "B", baseLength = "456", baseTitle = "n"),
        Shiur(baseSpeaker = "B", baseLength = "456", baseTitle = "o"),
        Shiur(baseSpeaker = "B", baseLength = "123", baseTitle = "l"),
        Shiur(baseSpeaker = "C", baseLength = "123", baseTitle = "u"),
        Shiur(baseSpeaker = "B", baseLength = "789", baseTitle = "q"),
        Shiur(baseSpeaker = "C", baseLength = "123", baseTitle = "s"),
        Shiur(baseSpeaker = "C", baseLength = "123", baseTitle = "t"),
        Shiur(baseSpeaker = "A", baseLength = "456", baseTitle = "f"),
    )
    val recyclerViewDummy: RecyclerView.Adapter<RecyclerView.ViewHolder?>? = null
    list.sort(
        recyclerViewDummy,
        mapOf(
            ShiurFilterOption.SPEAKER to true,
            ShiurFilterOption.LENGTH to false,
            ShiurFilterOption.TITLE to true
        )
    )
    println("copyList.toList():            ${list.toList()}")
    println("whatTheListShouldBe.toList(): ${whatTheListShouldBe.toList()}")
    println()
    println("Copy list: ")
    for (shiur in list) {
        println(shiur)
    }*/
    val mutableListOf = mutableListOf(true, true, true)
    println(mutableListOf)
    mutableListOf.myReplaceAll{false}
    println(mutableListOf)
//    println(mutableListOf(1,2,3).doubled())
//    println(mutableListOf("1a","2a","3a").doubled())
}
private fun <E> MutableList<E>.doubled(): MutableList<E> {
    return this.toMutableList().also{for(element in this) it.add(element)}
}
fun <E> MutableList<E>.myReplaceAll(operator: (E?) -> E) {
    Objects.requireNonNull(operator)
    val li: MutableListIterator<E> = this.listIterator()
    while (li.hasNext()) {
        li.set(operator(li.next()))
    }
}
/*when (this) {

    ShiurFilterOption.SPEAKER -> Shiur::baseSpeaker
    ShiurFilterOption.LENGTH -> Shiur::baseLength
    ShiurFilterOption.TITLE -> Shiur::baseTitle
    else -> Shiur::baseId
}as KProperty1<OneOfMyClasses, String?>*/

fun <T> T.println() = println(this)
fun <T> T.println(returnInstance: Boolean) = this.apply { println(this) }


public inline fun <T> List<T>.myforEach(action: (T) -> Unit): List<T> {
    for (element in this) action(element)
    return this
}