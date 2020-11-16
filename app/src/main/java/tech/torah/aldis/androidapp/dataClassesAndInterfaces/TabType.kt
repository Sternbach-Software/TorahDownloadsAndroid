package tech.torah.aldis.androidapp.dataClassesAndInterfaces

import tech.torah.aldis.androidapp.R

enum class TabType(val id:Int, val nameId:Int) {
    ALL(0, R.string.tab_name_all),
    CATEGORY(1, R.string.tab_name_category),
    SPEAKER(2, R.string.tab_name_speaker),
    SERIES(3, R.string.tab_name_series)
    //TODO should speaker be before category or vice versa?
}