package tech.torah.aldis.androidapp.dataClassesAndInterfaces

/**
 * A class which implements this interface indicates that it can be filtered by a [TorahFilter].
 * I assume that the filtering will be done by the adapter, which will have access to
 * the original list  and working list, so these functions don't need access to them
 *
 * */
interface TorahFilterable {
    //    fun callbackFilter(tabType: ShiurFilterOption, data: String, filterWithinPreviousResults: Boolean = false)
    /**
     * This is for activites like the speaker page, which don't have an associated ShiurFilterOption.
     * */
    /*fun *//*<T, VH : RecyclerView.VpViewHolder>*//* filter(
        constraint: String*//*,
        originalList: List<T>,
        workingList: MutableList<T>,
        recyclerView: RecyclerView.Adapter<VH>,
        filterWithinPreviousResults: Boolean = false,
        animation: Boolean = false,
        tabType: ShiurFilterOption = ShiurFilterOption.NONE,
        exactMatch: Boolean = false*//*
    )*/
    /**
     * This is for most activities, which need to know which [ShiurFilterOption] is being filtered for. filter(constraint: String) will call through to this function, passing ShiurFilterOption as ShiurFilterOption.NONE
     * Most of the time, [filter] is being called by ChooserFastScrollerDialog which should be
     an exact match, so that is the default.
     */
    fun /*<T, VH : RecyclerView.VpViewHolder>*/ filter(
        constraint: String,
        /*originalList: List<T>,
        workingList: MutableList<T>,
        recyclerView: RecyclerView.Adapter<VH>,
        filterWithinPreviousResults: Boolean = false,
        animation: Boolean = false,*/
        shiurFilterOption: ShiurFilterOption = ShiurFilterOption.NONE,
        exactMatch: Boolean = true,
//        exactMatch: Boolean = false
    )

    fun /*<T, VH : RecyclerView.VpViewHolder>*/ reset(/*
        originalList: MutableList<T>,
        workingList: MutableList<T>,
        recyclerView: RecyclerView.Adapter<VH>*/
    )
    /**
    * This function is used by [SearchView]s, so the list should be filterd by a partial match.
    */
    fun search(
        constraint: String,
        /*originalList: List<T>,
        workingList: MutableList<T>,
        recyclerView: RecyclerView.Adapter<VH>,
        filterWithinPreviousResults: Boolean = false,
        animation: Boolean = false,*/){
        filter(constraint,exactMatch = false)
    }
}