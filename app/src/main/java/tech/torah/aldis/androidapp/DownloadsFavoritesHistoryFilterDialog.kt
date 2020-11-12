package tech.torah.aldis.androidapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment


/**
 * This is the dialog that will be used for filtering the Downloads, Favorites, and History pages*/
class SortOrFilterFullScreenDialog(private val callbackListener: CallbackListener) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.sort_or_filter_dialog, container, false)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val andOrDropdown = listOf("AND","OR")
        val speakerDropdown = listOf("Rabbi Gedaliah Anemer", "Rabbi Aaron Lopiansky","a")
        val categoryDropdown = listOf("Kil'ayim", "Mikvaos","a")
        val seriesOrder = listOf("Daf Yomi", "Amud Yomi","a")
        val alphabeticalOrder = listOf("Descending", "Ascending","a")

        setAdapterAndDefault(speakerDropdown, view,R.id.speaker_dropdown)
        setAdapterAndDefault(categoryDropdown, view,R.id.category_dropdown)
        setAdapterAndDefault(seriesOrder, view,R.id.series_dropdown)

        setAdapterAndDefault(andOrDropdown, view,R.id.speaker_and_or_dropdown)
        setAdapterAndDefault(andOrDropdown, view,R.id.category_and_or_dropdown)
        setAdapterAndDefault(andOrDropdown, view,R.id.series_and_or_dropdown)

        setAdapterAndDefault(alphabeticalOrder, view,R.id.alphabetical_order_dropdown)


        val filterButton = view.findViewById<Button>(R.id.filter_button)
        val cancelButton = view.findViewById<Button>(R.id.cancel_button)
        filterButton.setOnClickListener {
            //send back data to PARENT fragment using callback
//            callbackListener.onDataReceived(view.findViewById<EditText>(R.id.editText).text.toString())
            // Now dismiss the fragment
            dismiss()
        }
        cancelButton.setOnClickListener {
            // Dismiss the fragment
            dismiss()
        }

    }

    private fun setAdapterAndDefault(
        filterCondition: List<String>,
        view: View,
        viewResId:Int
    ) {
        val alphabeticalAdapter =
            context?.let {
                ArrayAdapter<String>(
                    it,
                    R.layout.sort_or_filter_dropdown_menu_item,
                    filterCondition
                )
            }

        val dropDownMenu: AutoCompleteTextView =
            view.findViewById(viewResId)
        dropDownMenu.setText(filterCondition[1], false)
        dropDownMenu.setAdapter(alphabeticalAdapter)
    }
}