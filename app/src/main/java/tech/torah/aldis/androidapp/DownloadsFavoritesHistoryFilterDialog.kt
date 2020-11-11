package tech.torah.aldis.androidapp

import android.content.res.Resources
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
        val alphabeticalOrder = listOf("Descending", "Ascending")

        val adapter =
            context?.let {
                ArrayAdapter<String>(
                    it,
                    R.layout.sort_or_filter_dropdown_menu_item,
                    alphabeticalOrder
                )
            }

        val editTextFilledExposedDropdown: AutoCompleteTextView = view.findViewById(R.id.filled_exposed_dropdown)

        editTextFilledExposedDropdown.setText(alphabeticalOrder[0],false)
        editTextFilledExposedDropdown.setAdapter(adapter)
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
}