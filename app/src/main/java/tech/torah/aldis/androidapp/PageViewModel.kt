package tech.torah.aldis.androidapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur

class PageViewModel : ViewModel() {

    private val _tabType = MutableLiveData<ShiurFilterOption>()
    private val _shiurim = MutableLiveData<List<Shiur>>()
/*    val cardsForRecyclerView: LiveData<List<ShiurFullPage>> = Transformations.map(_tabType) {
        when(it.id){
            0->
        }
    }*/

    fun setTabTypeAndTorahFilter(shiurFilterOption: ShiurFilterOption, shiurim: List<Shiur>, categoryId:Int, speakerId:Int, ) {
        _tabType.value = shiurFilterOption
        _shiurim.value = shiurim

    }
}