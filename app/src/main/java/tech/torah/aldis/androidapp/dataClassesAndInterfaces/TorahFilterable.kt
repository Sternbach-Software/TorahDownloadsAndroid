package tech.torah.aldis.androidapp.dataClassesAndInterfaces
/**
 * A class which implements this interface indicates that it can be filtered by a [TorahFilter].
 * Currently only used as a callback for the [SortOrFilterDialog]
 * */
interface TorahFilterable {
    fun callbackFilter(tabType: TabType, data: String)
}