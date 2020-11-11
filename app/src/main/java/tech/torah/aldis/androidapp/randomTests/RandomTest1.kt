package tech.torah.aldis.androidapp.randomTests

import java.io.File
import java.text.DecimalFormat


fun main() {
    var linesOfCode = 0
    for (file in File("C:\\Users\\shmue\\AndroidStudioProjects\\vlc-android\\application\\vlc-android\\src").walk().toList().filter{it.extension=="kt"}) {
        linesOfCode+=file.readLines().size
    }
    println(linesOfCode)
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
