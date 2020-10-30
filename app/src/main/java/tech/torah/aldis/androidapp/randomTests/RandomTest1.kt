package tech.torah.aldis.androidapp.randomTests

import java.text.DecimalFormat


fun main() {
    val decimalFormat = DecimalFormat("#,###.00")
    val resultSubTotalAmount = 12345678901 / 100
    val resultFormatSubTotalAmount = decimalFormat.format(resultSubTotalAmount)
    println(resultFormatSubTotalAmount)
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
