package tech.torah.aldis.androidapp.dataClassesAndInterfaces

import tech.torah.aldis.androidapp.R
/**
 * This enum was originally intended for a 3-tab layout, but the design was reconsidered.
 * It now functions as an enum to enumerate different subjects of which a shiur can be filtered
 * @property NONE functions as a default for the filter function to indicate that
 * the shiur(im) is not being filtered by its category, speaker, etc.
 *
 * */
enum class TabType(val id:Int, val nameId:Int) {
    NONE(0, R.string.tab_name_none),
    CATEGORY(1, R.string.tab_name_category),
    SPEAKER(2, R.string.tab_name_speaker),
    SERIES(3, R.string.tab_name_series)
    //TODO should speaker be before category or vice versa?
}