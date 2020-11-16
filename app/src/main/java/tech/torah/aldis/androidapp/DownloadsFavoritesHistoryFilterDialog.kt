package tech.torah.aldis.androidapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.android.material.button.MaterialButton
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CallbackListener


/**
 * This is the dialog that will be used for filtering the Downloads, Favorites, and History pages*/
class SortOrFilterFullScreenDialog(private val callbackListener: CallbackListener) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = true
        return inflater.inflate(R.layout.simple_sort_or_filter_dialog, container, false)
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filterButton = view.findViewById<MaterialButton>(R.id.filter_button_simple)
        val cancelButton = view.findViewById<MaterialButton>(R.id.cancel_button_simple)
        val filterRadioButton = view.findViewById<RadioButton>(R.id.filter_radio_button_simple)
        val sortRadioButton = view.findViewById<RadioButton>(R.id.sort_radio_button_simple)
        val sortOrderTextInputLayout = view.findViewById<TextInputLayout>(R.id.sort_order_text_input_layout_simple)
        val sortOrderDropdown = view.findViewById<AutoCompleteTextView>(R.id.sort_order_dropdown_simple)
        val filterCriterionChooser = view.findViewById<AutoCompleteTextView>(R.id.filter_criterion_chooser)
        val individualSpeakerCategorySeriesChooser = view.findViewById<AutoCompleteTextView>(R.id.individual_speaker_category_series_chooser)
        val individualSpeakerCategorySeriesTextInputLayout = view.findViewById<TextInputLayout>(R.id.individual_speaker_category_series_text_input_layout)

        val listOfFilterCriterion = listOf("Speaker","Category","Series")
        val listOfAvailableSpeakers = listOf("Rabbi Aaron Lopiansky", "Rabbi Gedaliah Anemer", "a")
        val listOfAvailableCategories = listOf("Kil'ayim", "Mikvaos", "a")
        val listOfAvailableSeries = listOf("Amud Yomi", "Daf Yomi", "a")

        setAdapterAndSetHints(filterCriterionChooser, listOfFilterCriterion) //TODO somehow also change the hint for individualSpeakerCategorySeriesTextInputLayout (hence "...Hint*s*(...)". Possibly create a Map<speakerCategorySeries :String, listOfAvailable... :List<String>>
        filterRadioButton.setOnClickListener {

            filterButton.text = "Filter"
            //sort order inapplicable by filtering, so hide sort order dropdown
            sortOrderDropdown.visibility = View.GONE
            sortOrderTextInputLayout.visibility = View.GONE
        }
        sortRadioButton.setOnClickListener {

            filterButton.text = "Sort"
            sortOrderDropdown.visibility = View.VISIBLE
            sortOrderTextInputLayout.visibility = View.VISIBLE

        }
        filterCriterionChooser.setOnItemClickListener { _, _, position, _ ->
           val (appropriateList,hint) = when(position){
                0 -> listOf(listOfAvailableSpeakers,"Speaker")
                1 -> listOf(listOfAvailableCategories,"Category")
                2 -> listOf(listOfAvailableSeries,"Series")
               else -> listOf(listOf<String>(),"")
           }
            individualSpeakerCategorySeriesChooser.apply{
                setText("") //clear previous entry

                setAdapter(getDropdownAdapter(appropriateList as List<String>))

                setHint(hint as String)
                individualSpeakerCategorySeriesTextInputLayout.hint = "" //Otherwise causes text-over-text
            }
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

        filterRadioButton.performClick() //Indicate that dialog default is filtering
    }

    private fun setAdapterAndSetHints(
        viewResId: Int,
        filterCondition: List<String>,
        view: View
    ) {
        val alphabeticalAdapter =
            getDropdownAdapter(filterCondition)

        val dropDownMenu: AutoCompleteTextView =
            view.findViewById(viewResId)
        dropDownMenu.setText(filterCondition[1], false)
        dropDownMenu.setAdapter(alphabeticalAdapter)
    }
    private fun setAdapterAndSetHints(
        dropDownMenu: AutoCompleteTextView,
        filterCondition: List<String>
    ) {
        val alphabeticalAdapter =
            getDropdownAdapter(filterCondition)
        dropDownMenu.setText(filterCondition[0], false)

        dropDownMenu.setAdapter(alphabeticalAdapter)
    }

    private fun getDropdownAdapter(filterCondition: List<String>): ArrayAdapter<String>? {
        return context?.let {
            ArrayAdapter<String>(
                it,
                R.layout.sort_or_filter_dropdown_menu_item,
                filterCondition
            )
        }
    }
}
/*  //setup for advanced_sort_or_filter_dialog
       val seriesCheckbox = view.findViewById<MaterialCheckBox>(R.id.series_checkbox)
       val andOrDropdown = listOf("OR","AND")
       val speakerDropdown = listOf("Rabbi Gedaliah Anemer", "Rabbi Aaron Lopiansky", "a")
       val categoryDropdown = listOf("Kil'ayim", "Mikvaos", "a")
       val seriesOrder = listOf("Daf Yomi", "Amud Yomi", "a")
       val alphabeticalOrder = listOf("Descending", "Ascending", "a")

       setAdapterAndDefault(R.id.speaker_dropdown, speakerDropdown, view)
       setAdapterAndDefault(R.id.category_dropdown, categoryDropdown, view)
       setAdapterAndDefault(R.id.series_dropdown, seriesOrder, view)

       setAdapterAndDefault(andOrDropdown, view, R.id.speaker_and_or_dropdown)
       setAdapterAndDefault(R.id.category_and_or_dropdown, andOrDropdown, view)
       setAdapterAndDefault(R.id.series_and_or_dropdown, andOrDropdown, view)
       setAdapterAndDefault(R.id.alphabetical_order_dropdown, alphabeticalOrder, view)


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
   answer: figured out that you can call callOnClick()
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
*/