package tech.torah.aldis.androidapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.android.material.button.MaterialButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputLayout


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
        val andOrDropdown = listOf("OR","AND")
        val speakerDropdown = listOf("Rabbi Gedaliah Anemer", "Rabbi Aaron Lopiansky", "a")
        val categoryDropdown = listOf("Kil'ayim", "Mikvaos", "a")
        val seriesOrder = listOf("Daf Yomi", "Amud Yomi", "a")
        val alphabeticalOrder = listOf("Descending", "Ascending", "a")

        setAdapterAndDefault(speakerDropdown, view, R.id.speaker_dropdown)
        setAdapterAndDefault(categoryDropdown, view, R.id.category_dropdown)
        setAdapterAndDefault(seriesOrder, view, R.id.series_dropdown)

        setAdapterAndDefault(andOrDropdown, view, R.id.speaker_and_or_dropdown)
        setAdapterAndDefault(andOrDropdown, view, R.id.category_and_or_dropdown)
        setAdapterAndDefault(andOrDropdown, view, R.id.series_and_or_dropdown)
        setAdapterAndDefault(alphabeticalOrder, view, R.id.alphabetical_order_dropdown)


        val seriesCheckbox = view.findViewById<MaterialCheckBox>(R.id.series_checkbox)
        val filterButton = view.findViewById<MaterialButton>(R.id.filter_button)
        val cancelButton = view.findViewById<MaterialButton>(R.id.cancel_button)
        val filterRadioButton = view.findViewById<RadioButton>(R.id.filter_radio_button)
        val filterSortRadioGroup = view.findViewById<RadioGroup>(R.id.filter_sort_radio_group)
        val sortRadioButton = view.findViewById<RadioButton>(R.id.sort_radio_button)

        val viewTreeObserver = view.viewTreeObserver
        if (viewTreeObserver.isAlive) {
            viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    Log.d(
                        "FilterDialog",
                        "Category text input height: " + view.findViewById<TextInputLayout>(R.id.category_text_input_layout).height
                    )
                    Log.d(
                        "FilterDialog",
                        "Category dropdown height: " + view.findViewById<AutoCompleteTextView>(R.id.category_dropdown).height
                    )
// TODO refactor setting default radio button (is it possible to do in code? no call to check() seems to work, even in tree observer,  and respective click listeners and setText() (for filter/sort button at bottom) to function to modular/make easy to change                   filterSortRadioGroup.check(R.id.sort_radio_button) //Indicate that dialog default setting is filtering, to match with XML title attr (had to set something as default)
                }
            })
        }

        seriesCheckbox.setOnCheckedChangeListener{ compoundButton, boolean ->
            when (boolean){
                //TODO put this when inside of the check() call
                true -> filterSortRadioGroup.check(R.id.sort_radio_button)
                false ->filterSortRadioGroup.check(R.id.filter_radio_button)
            }
        }
        filterRadioButton.setOnClickListener{
            filterButton.text = "Filter"
        }
        sortRadioButton.setOnClickListener{
            filterButton.text = "Sort"
        }
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
        viewResId: Int
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