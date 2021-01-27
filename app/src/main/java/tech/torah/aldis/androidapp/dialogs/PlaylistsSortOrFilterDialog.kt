package tech.torah.aldis.androidapp.dialogs

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CallbackListener
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import java.util.*


/**
 * This is the dialog that will be used for filtering the Downloads, Favorites, and History pages
 * */
private const val TAG = "PlaylistsSortOrFilterDialogFragment"

class PlaylistsSortOrFilterDialog(
    private val torahFilterableCallback: TorahFilterable,
    private val listOfSpeakerNames: List<String>,
    private val listOfCategoryNames: List<String>,
    private val listOfSeriesNames: List<String>
) :
    DialogFragment(), CallbackListener /*TODO consider making this a subclass of a BaseSortOrFilterDialog*/ {
    private val listOfPossibleListItems = listOf(
        listOfSpeakerNames,
        listOfCategoryNames,
        listOfSeriesNames
    )
    //private lateinit var progressiveFilterExplanationImageButton: ImageButton
    private lateinit var filterButton: MaterialButton
    private lateinit var cancelButton: MaterialButton
    private lateinit var resetButton: MaterialButton
    //private lateinit var filterWithinPreviousResultsCheckBox: MaterialCheckBox
    private lateinit var filterRadioButton: RadioButton
    private lateinit var sortRadioButton: RadioButton
    private lateinit var sortOrderTextInputLayout: TextInputLayout
    private lateinit var sortOrderDropdown: AutoCompleteTextView
    private lateinit var filterCriterionChooser: AutoCompleteTextView
    private lateinit var individualSpeakerCategorySeriesChooser: TextInputLayout
    private lateinit var individualSpeakerCategorySeriesChooserAutoCompleteTextView: AutoCompleteTextView
    private lateinit var shiurFilterOptionBeingDisplayed: ShiurFilterOption
    private lateinit var fastScrollerDialogListItems: List<String>

    //private var filteringWithinPreviousResultsEnabled = false //by default
    private var selectedListItem = ""


    private val listOfFilterCriterion: List<String> = listOf("Length", "Number of total shiurim", "Number of completed shiurim", "Number of speakers?")


    //TODO add parameter in primary constructor for list of possible speakers, categories, and series.
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

        //<editor-fold desc="variable initializations">
//        progressiveFilterExplanationImageButton = view.findViewById(R.id.progressive_filter_explanation_image_button)
        filterButton = view.findViewById(R.id.filter_button_simple)
        cancelButton = view.findViewById(R.id.cancel_button_simple)
        resetButton = view.findViewById(R.id.reset_button_simple)
        /*filterWithinPreviousResultsCheckBox =
            view.findViewById(R.id.filter_within_previous_results_checkbox)
        */filterRadioButton = view.findViewById(R.id.filter_radio_button_simple)
        sortRadioButton = view.findViewById(R.id.sort_radio_button_simple)
        sortOrderTextInputLayout = view.findViewById(R.id.sort_order_text_input_layout_simple)
        sortOrderDropdown = view.findViewById(R.id.sort_order_dropdown_simple)
        filterCriterionChooser = view.findViewById(R.id.filter_criterion_chooser)
        individualSpeakerCategorySeriesChooser =
            view.findViewById(R.id.individual_filter_criterion_chooser_text_input_layout)
        individualSpeakerCategorySeriesChooserAutoCompleteTextView =
            view.findViewById(R.id.individual_filter_criterion_chooser_chooser_auto_complete_text_view)


        //</editor-fold>

        setAdapterAndSetHints(
            filterCriterionChooser,
            listOfFilterCriterion
        )

        //<editor-fold desc="listeners">
       /* progressiveFilterExplanationImageButton.setOnClickListener {
            val alertDialog: AlertDialog? =
                context?.let { it1 -> AlertDialog.Builder(it1).create() }
            alertDialog?.setTitle("Progressive Filtering")
            alertDialog?.setMessage("")
            alertDialog?.setButton(
                AlertDialog.BUTTON_NEUTRAL, "OK"
            ) { dialog, _ -> dialog.dismiss() }
            alertDialog?.setCanceledOnTouchOutside(true)
            alertDialog?.show()
        }*/
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
            /*  inlined for efficiency
            val speakerString = resources.getString(ShiurFilterOption.SPEAKER.nameId)
            val categoryString = resources.getString(ShiurFilterOption.CATEGORY.nameId)
            val seriesString = resources.getString(ShiurFilterOption.SERIES.nameId)*/
            val (appropriateList, tabType) = when (position) {
                0 -> listOf(
                    listOfSpeakerNames,
                    resources.getString(ShiurFilterOption.SPEAKER.nameStringResourceId)
                ) //calls to getString() are good for DRY and localization
                1 -> listOf(listOfCategoryNames, resources.getString(ShiurFilterOption.CATEGORY.nameStringResourceId))
                2 -> listOf(listOfSeriesNames, resources.getString(ShiurFilterOption.SERIES.nameStringResourceId))
                else -> listOf(listOf<String>(), "")
            }
            shiurFilterOptionBeingDisplayed = ShiurFilterOption.valueOf((tabType as String).toUpperCase(Locale.ROOT))
            if (selectedListItem != "") individualSpeakerCategorySeriesChooserAutoCompleteTextView.setText(
                ""
            )
            individualSpeakerCategorySeriesChooserAutoCompleteTextView.hint = "$tabType..."
            fastScrollerDialogListItems = (appropriateList as List<String>)
            //TODO populate speaker/category/series popup with appropriate data
        }
        val populateAndDisplayFastScrollerDialogLambda = { _: View ->
            Log.d(TAG, "individualSpeakerCategorySeriesChooser clicked")

/*
            val randomWord = { length:Int ->
                val allowedChars = ('A'..'Z') + ('a'..'z')
                (1..length)
                    .map { allowedChars.random() }
                    .joinToString("")
            }
            val randomListOfWords = mutableListOf<String>()
            for (i in 1..500) randomListOfWords.add(randomWord(5))


             fastScrollerDialogListItems = randomListOfWords.sorted()*/
            if (::shiurFilterOptionBeingDisplayed.isInitialized || ::fastScrollerDialogListItems.isInitialized) ChooserFastScrollerDialog( // would use &&, but if one is true, both are true, and && will always check both, but || will only check the second if the first is false, and I think that if the first is false, the second must also be false, but I am not 100% sure.
                fastScrollerDialogListItems,
                shiurFilterOptionBeingDisplayed,
                this
            ).show(
                childFragmentManager,
                TAG
            )
        }
        individualSpeakerCategorySeriesChooserAutoCompleteTextView.setOnClickListener(
            populateAndDisplayFastScrollerDialogLambda
        )
        individualSpeakerCategorySeriesChooser.setEndIconOnClickListener(
            populateAndDisplayFastScrollerDialogLambda
        )  // so that the user is not confused when clicking the end icon doesn't display the dialog, but the main body of the OutlinedBox does display it.
        filterButton.setOnClickListener {
            torahFilterableCallback.filter(selectedListItem, shiurFilterOptionBeingDisplayed, true)
            //send back data to PARENT fragment using callback
            // Now dismiss the fragment
            dismiss()
        }
        cancelButton.setOnClickListener {
            // Dismiss the fragment
            dismiss()
        }
        resetButton.setOnClickListener {
            //send back data to PARENT fragment using callback
            torahFilterableCallback.reset()
            // Now dismiss the fragment
            dismiss()
        }
        /*filterWithinPreviousResultsCheckBox.setOnCheckedChangeListener { _, isChecked ->
            filteringWithinPreviousResultsEnabled = when (isChecked) {
                true -> true
                false -> false
            }
        }*/
        filterRadioButton.performClick() //Indicate that dialog default is filtering
        //</editor-fold>
    }

    //<editor-fold desc="set hint and adapter functions">
    private fun setAdapterAndSetHints(
        viewResId: Int,
        filterCondition: List<String>,
        view: View
    ) {
        val alphabeticalAdapter =
            getDropdownAdapter(filterCondition)
        val secondCondition = filterCondition[1]
        val dropDownMenu: AutoCompleteTextView =
            view.findViewById(viewResId)
        dropDownMenu.setText(secondCondition, false)
        individualSpeakerCategorySeriesChooserAutoCompleteTextView.hint = secondCondition
        dropDownMenu.setAdapter(alphabeticalAdapter)
    }

    private fun setAdapterAndSetHints(
        dropDownMenu: AutoCompleteTextView,
        filterCondition: List<String>
    ) {
        val alphabeticalAdapter =
            getDropdownAdapter(filterCondition)
        val firstCondition = filterCondition[0]
        dropDownMenu.setText(firstCondition, false)

        individualSpeakerCategorySeriesChooserAutoCompleteTextView.hint = "$firstCondition..."
        shiurFilterOptionBeingDisplayed = ShiurFilterOption.valueOf(firstCondition.toUpperCase(Locale.ROOT))
        fastScrollerDialogListItems = listOfPossibleListItems[0]
        dropDownMenu.setAdapter(alphabeticalAdapter)
    }
//</editor-fold>

    private fun getDropdownAdapter(filterCondition: List<String>): ArrayAdapter<String>? {
        return context?.let {
            ArrayAdapter<String>(
                it,
                R.layout.sort_or_filter_dropdown_menu_item,
                filterCondition
            )
        }
    }

    override fun onDataReceived(data: String) {
        selectedListItem = data
        individualSpeakerCategorySeriesChooserAutoCompleteTextView.setText(data)
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