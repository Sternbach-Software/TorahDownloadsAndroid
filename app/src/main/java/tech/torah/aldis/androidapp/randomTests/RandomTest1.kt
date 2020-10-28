package tech.torah.aldis.androidapp.randomTests

import java.io.File

fun main() {
    val file =
        File("C:\\My Web Sites\\torahdownloadspictures\\torahdownloads.com\\assets\\speakers").walk()
            .toList()
    for (i in 0..68) {
        val x = mutableListOf<String>()
        ('a'..'z').toList().forEach { it1 ->
            ('a'..'z').toList().forEach { it2 ->
                x.add("$it1$it2")
            }
        }
//        file[i].renameTo(File("C:\\My Web Sites\\torahdownloadspictures\\torahdownloads.com\\assets\\speakers\\${x[i]}.jpg"))
        println("R.drawable.${x[i]}"
        )
    }
}