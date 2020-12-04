package tech.torah.aldis.androidapp.dataClassesAndInterfaces

interface CallbackListener {
    fun onDataReceived(tabType: TabType, data: String)
}