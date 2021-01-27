package tech.torah.aldis.androidapp.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.airbnb.paris.extensions.style
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputLayout
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar.OnRangeSeekBarChangeListener
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CallbackListener
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.formatted
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.getShiurFilterOptionString
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.toHrMinSec
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.mEntireApplicationContext


private const val TAG = "ShiurimSortOrFilterDial"

/**
 * This is the dialog that will be used for filtering the pages which can filterable shiurim
 * (e.g. Downloads, Favorites, List of speaker's shiurim,etc.)
 * */
class ShiurimSortOrFilterDialog(
    private val torahFilterableCallback: TorahFilterable,
    private val mapOfFilterCriteriaData: Map<ShiurFilterOption, List<String>>//e.g. "Speaker" to listOf("Rabbi...",...)
) : DialogFragment(), CallbackListener /*TODO consider making this a subclass of a BaseSortOrFilterDialog*/{
    //private lateinit var progressiveFilterExplanationImageButton: ImageButton
    private lateinit var filterButton: MaterialButton
    private lateinit var cancelButton: MaterialButton
    private lateinit var resetButton: MaterialButton

    //private lateinit var filterWithinPreviousResultsCheckBox: MaterialCheckBox
    private lateinit var filterRadioButton: RadioButton
    private lateinit var sortRadioButton: RadioButton
    private lateinit var sortOrderTextInputLayout: TextInputLayout
    private lateinit var sortOrderDropdown: AutoCompleteTextView
    private lateinit var filterCriterionChooser: AutoCompleteTextView       //This has options like "Speaker","Category", etc.
    private lateinit var individualFilterCriterionChooser: TextInputLayout  //This has options like "Noach","Lech Lecha", etc.
    private lateinit var individualFilterCriterionChooserAutoCompleteTextView: AutoCompleteTextView
    private lateinit var linearLayoutForUseSeekbarSwitch: LinearLayout
    private lateinit var linearLayoutForSeekbar: LinearLayout
    private lateinit var rangeSeekBar: RangeSeekBar
    private lateinit var useSeekbarSwitch: SwitchMaterial
    private lateinit var switchTextView: TextView
    private lateinit var seekbarStartProgressIndicator: TextView
    private lateinit var seekbarEndProgressIndicator: TextView
    private lateinit var shiurFilterOptionBeingDisplayed: ShiurFilterOption
    private lateinit var fastScrollerDialogListItems: List<String>
    private var listOfFilterCriteriaKeys: List<ShiurFilterOption> =
        mapOfFilterCriteriaData.keys.toList()
    private var listOfFilterCriteriaValues: List<List<String>> =
        mapOfFilterCriteriaData.values.toList()
    private var toUseSeekBar = true //set range seekbar enabled by default

    //private var filteringWithinPreviousResultsEnabled = false //by default
    private var selectedListItem = ""

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
        individualFilterCriterionChooser =
            view.findViewById(R.id.individual_filter_criterion_chooser_text_input_layout)
        individualFilterCriterionChooserAutoCompleteTextView =
            view.findViewById(R.id.individual_filter_criterion_chooser_chooser_auto_complete_text_view)
        rangeSeekBar = view.findViewById(R.id.rangeSeekBar)
        seekbarStartProgressIndicator = view.findViewById(R.id.seekbar_start_progress_indicator)
        seekbarEndProgressIndicator = view.findViewById(R.id.seekbar_end_progress_indicator)
        linearLayoutForSeekbar = view.findViewById(R.id.linear_layout_for_seekbar)
        linearLayoutForUseSeekbarSwitch = view.findViewById(R.id.linear_layout_for_use_seekbar_switch)
        useSeekbarSwitch = view.findViewById(R.id.use_seekbar_switch)
        switchTextView = view.findViewById(R.id.switch_text_view)

        //</editor-fold>

        setAdapterAndSetHints(
            filterCriterionChooser,
            listOfFilterCriteriaKeys
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

            val appropriateList = listOfFilterCriteriaValues[position]
            val shiurFilterOption = listOfFilterCriteriaKeys[position]
            if(shiurFilterOption!=ShiurFilterOption.LENGTH && (linearLayoutForSeekbar.visibility==View.VISIBLE || linearLayoutForUseSeekbarSwitch.visibility == View.VISIBLE)){//reset the visibility of the special views for
                toggleUseSeekbar(toUseSeekBar) //redraw individualFilterCriterionChooser and hide linearLayoutForSeekbar
                    //TODO for some reason, I am not getting the logic right because when you click the useSeekBar switch and then go to a different ShiurFilterOption, the linearLayoutForSeekbar still appears in place of the filterCriterionChooser.
//                useSeekbarSwitch.isChecked = toUseSeekBar
                linearLayoutForUseSeekbarSwitch.visibility = View.GONE
            }
            shiurFilterOptionBeingDisplayed = shiurFilterOption
            if (selectedListItem != "") individualFilterCriterionChooserAutoCompleteTextView.setText(
                ""
            )
            if (shiurFilterOptionBeingDisplayed == ShiurFilterOption.HAS_ATTACHMENT || shiurFilterOptionBeingDisplayed == ShiurFilterOption.HAS_DESCRIPTION) {
                Log.d(TAG, "Is HAS_ATTACHMENT or HAS_DESCRIPTION")
                individualFilterCriterionChooser.style(R.style.Widget_MaterialComponents_TextInputLayout_FilledBox_Dense)
                individualFilterCriterionChooser.tag = true
                individualFilterCriterionChooserAutoCompleteTextView.setAdapter(
                    getYesNoDropdownAdapter()
                )
            }
            else if(shiurFilterOptionBeingDisplayed == ShiurFilterOption.LENGTH){
                linearLayoutForUseSeekbarSwitch.visibility = View.VISIBLE
                toggleUseSeekbar(toUseSeekBar) //set range seekbar enabled by default
                useSeekbarSwitch.isEnabled = toUseSeekBar
                rangeSeekBar.apply{
                    val listOfLengths =  mapOfFilterCriteriaData[ShiurFilterOption.LENGTH]?.map{it.toInt()}
                    val minLength = listOfLengths?.minOrNull() ?: 0 //TODO not sure what the best thing to do would be if these are null
                    val maxLength = listOfLengths?.maxOrNull() ?: 0
                    val i = maxLength / 3
                    max = maxLength
                    setProgress(minLength + i, maxLength - i) //i want it a third from the beggining and a thrid from the end. E.g. if it is 0..30, then thumbs at 10 and 20 to show the user what can be done
                }
            }
            else {
                if (individualFilterCriterionChooser.tag == true) /*the style was changed*/ {
                    /*reset the style*/
                        Log.d(TAG, "Style was changed")
                        individualFilterCriterionChooser.style(
                            R.style.DropdownMenuWithNoEndIcon
                        )
                }
                individualFilterCriterionChooser.tag = false
            }
            individualFilterCriterionChooserAutoCompleteTextView.hint = "${shiurFilterOption.getShiurFilterOptionString()}..."
            fastScrollerDialogListItems = appropriateList
            //TODO populate speaker/category/series popup with appropriate data
        }
        val populateAndDisplayFastScrollerDialogLambda = { autoCompleteTextView: View ->
            Log.d(TAG, "individualCriterionChooserAutoCompleteTextView clicked")

//          fastScrollerDialogListItems = CONSTANTS.listOfRandomWords(500,5).sorted()
            if (shiurFilterOptionBeingDisplayed == ShiurFilterOption.HAS_ATTACHMENT || shiurFilterOptionBeingDisplayed == ShiurFilterOption.HAS_DESCRIPTION) {
                //Don't do anything because this is a dropdown menu
//                onDataReceived((autoCompleteTextView as AutoCompleteTextView).text.toString())
            } else if (::shiurFilterOptionBeingDisplayed.isInitialized || ::fastScrollerDialogListItems.isInitialized) ChooserFastScrollerDialog( // would use &&, but if one is true, both are true, and && will always check both, but || will only check the second if the first is false, and I think that if the first is false, the second must also be false, but I am not 100% sure.
                fastScrollerDialogListItems,
                shiurFilterOptionBeingDisplayed,
                this
            ).show(
                childFragmentManager,
                TAG
            )
        }
        rangeSeekBar.setOnRangeSeekBarChangeListener(object : OnRangeSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: RangeSeekBar,
                progressStart: Int,
                progressEnd: Int,
                fromUser: Boolean
            ) {
                seekbarStartProgressIndicator.text = progressStart.toHrMinSec().formatted(false)
                seekbarEndProgressIndicator.text = progressEnd.toHrMinSec().formatted(false)
            }

            override fun onStartTrackingTouch(seekBar: RangeSeekBar) {}
            override fun onStopTrackingTouch(seekBar: RangeSeekBar) {}
        })
        useSeekbarSwitch.setOnCheckedChangeListener { buttonView, useSeekbar ->
            toggleUseSeekbar(useSeekbar)
            toUseSeekBar = useSeekbar
        }
        switchTextView.setOnClickListener{//so that the user doesn't have to reach over to toggle it (I instinctively thought that the textview would perform a click on the switch)
            useSeekbarSwitch.performClick()
        }
        individualFilterCriterionChooserAutoCompleteTextView.setOnClickListener(
            populateAndDisplayFastScrollerDialogLambda
        )
        individualFilterCriterionChooser.setEndIconOnClickListener(
            populateAndDisplayFastScrollerDialogLambda
        )  // so that the user is not confused when clicking the end icon doesn't display the dialog, but the main body of the OutlinedBox does display it.
        filterButton.setOnClickListener {
            //send back data to PARENT fragment using callback
            torahFilterableCallback.filter(selectedListItem, shiurFilterOptionBeingDisplayed, true)
            // Now dismiss the fragment
            dismiss()
        }
        cancelButton.setOnClickListener {
            dismiss()
        }
        resetButton.setOnClickListener {
            torahFilterableCallback.reset()
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

    private fun toggleUseSeekbar(useSeekbar: Boolean) {
        linearLayoutForSeekbar.visibility = if (useSeekbar) View.VISIBLE else View.GONE
        individualFilterCriterionChooser.visibility = if (useSeekbar) View.GONE else View.VISIBLE
    }

    //<editor-fold desc="set hint and adapter functions">
    private fun setAdapterAndSetHints(
        viewResId: Int,
        filterCondition: List<ShiurFilterOption>,
        view: View
    ) {
        TODO(
            "Outdated. Update this to match setAdapterAndSetHints(\n" +
                    "        dropDownMenu: AutoCompleteTextView,\n" +
                    "        filterCondition: List<String>\n" +
                    "    ) "
        )
        val alphabeticalAdapter =
            getDropdownAdapter(filterCondition)
        val secondCondition = filterCondition[1]
        val dropDownMenu: AutoCompleteTextView =
            view.findViewById(viewResId)
        dropDownMenu.setText(secondCondition.toString(), false)
        individualFilterCriterionChooserAutoCompleteTextView.hint = secondCondition.toString()
        dropDownMenu.setAdapter(alphabeticalAdapter)
    }

    private fun setAdapterAndSetHints(
        dropDownMenu: AutoCompleteTextView,
        filterConditions: List<ShiurFilterOption>
    ) {
        val dropDownAdapter = getDropdownAdapter(filterConditions)
        val firstCriterion = filterConditions[0] // set default as first filter criterion
        val shiurFilterOptionString = firstCriterion.getShiurFilterOptionString()
        dropDownMenu.setText(shiurFilterOptionString, false)
        individualFilterCriterionChooserAutoCompleteTextView.hint = "$shiurFilterOptionString..."
        shiurFilterOptionBeingDisplayed = firstCriterion
        fastScrollerDialogListItems = listOfFilterCriteriaValues[0]
        dropDownMenu.setAdapter(dropDownAdapter)
    }
//</editor-fold>

    private fun getDropdownAdapter(filterConditions: List<ShiurFilterOption>): ArrayAdapter<String>? {
        return context?.let {
            ArrayAdapter<String>(
                it,
                R.layout.sort_or_filter_dropdown_menu_item,
                filterConditions.toListOfStrings()
            )
        }
    }

    private fun getYesNoDropdownAdapter(): ArrayAdapter<String>? {
        return context?.let {
            ArrayAdapter<String>(
                it,
                R.layout.sort_or_filter_dropdown_menu_item,
                arrayOf("Yes", "No")
            )
        }
    }

    override fun onDataReceived(data: String) {
        selectedListItem = data
        individualFilterCriterionChooserAutoCompleteTextView.setText(data)
    }
}

private fun List<ShiurFilterOption>.toListOfStrings(): Array<String> {
    val mutableList = mutableListOf<String>()
    for (element in this) {
        mutableList.add(mEntireApplicationContext.resources.getString(element.nameStringResourceId))//TODO potentially use getShiurFilterOptionString()
    }
    return mutableList.toTypedArray()
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


