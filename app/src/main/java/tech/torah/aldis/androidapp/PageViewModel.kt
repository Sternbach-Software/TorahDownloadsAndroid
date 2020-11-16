package tech.torah.aldis.androidapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Shiur
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType

class PageViewModel : ViewModel() {

    private val _tabType = MutableLiveData<TabType>()
    private val _shiurim = MutableLiveData<List<Shiur>>()
/*    val cardsForRecyclerView: LiveData<List<Shiur>> = Transformations.map(_tabType) {
        when(it.id){
            0->
        }
    }*/

    fun setTabTypeAndTorahFilter(tabType: TabType, shiurim: List<Shiur>, categoryId:Int, speakerId:Int, ) {
        _tabType.value = tabType
        _shiurim.value = shiurim

    }
}