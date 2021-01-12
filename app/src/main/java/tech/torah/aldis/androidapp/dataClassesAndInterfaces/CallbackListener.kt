package tech.torah.aldis.androidapp.dataClassesAndInterfaces
/**
 * A general purpose callback listener. Currently, classes which utilize the [ChooserFastScrollerDialog]
 * implement this interface to set the [AutoCompleteTextView] text to the selected list item and
 * pass in the string to [FunctionLibrary.filter].
 * */
interface CallbackListener {
    fun onDataReceived(data:String)
}