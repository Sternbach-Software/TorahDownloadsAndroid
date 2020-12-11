package tech.torah.aldis.androidapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.l4digital.fastscroll.FastScrollView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import java.util.*


/**
 * This is the dialog that will be used for filtering the Downloads, Favorites, and History pages
 * */
private const val TAG = "DownloadsFavoritesHistoryFilterDialogFragment"
private lateinit var selectedListItemTextView: MaterialTextView
private lateinit var progressiveFilterExplanationImageButton: ImageButton
private lateinit var filterButton: MaterialButton
private lateinit var cancelButton: MaterialButton
private lateinit var resetButton: MaterialButton
private lateinit var filterRadioButton: RadioButton
private lateinit var fastScrollerSelectButton: MaterialButton
private lateinit var fastScrollerCancelButton: MaterialButton
private lateinit var fastScrollerDeselectButton: MaterialButton
private lateinit var sortRadioButton: RadioButton
private lateinit var sortOrderTextInputLayout: TextInputLayout
private lateinit var sortOrderDropdown: AutoCompleteTextView
private lateinit var filterCriterionChooser: AutoCompleteTextView
private lateinit var individualSpeakerCategorySeriesChooser: TextInputLayout
private lateinit var individualSpeakerCategorySeriesChooserAutoCompleteTextView: AutoCompleteTextView
private lateinit var tabTypeBeingDisplayed: TabType
private lateinit var fastScrollerDialogListItems: List<String>
private var selectedListItem = ""


val listOfFilterCriterion: List<String> = listOf("Speaker", "Category", "Series")


class SortOrFilterDialog(
    private val torahFilterableCallback: TorahFilterable,
    private val listOfSpeakerNames: List<String>,
    private val listOfCategoryNames: List<String>,
    private val listOfSeriesNames: List<String>
) :
    DialogFragment() {
    private val listOfPossibleListItems = listOf(
        listOfSpeakerNames,
        listOfCategoryNames,
        listOfSeriesNames
    )
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
        progressiveFilterExplanationImageButton =
            view.findViewById(R.id.progressive_filter_explanation_image_button)
        filterButton = view.findViewById(R.id.filter_button_simple)
        cancelButton = view.findViewById(R.id.cancel_button_simple)
        resetButton = view.findViewById(R.id.reset_button_simple)
        filterRadioButton = view.findViewById(R.id.filter_radio_button_simple)
        sortRadioButton = view.findViewById(R.id.sort_radio_button_simple)
        sortOrderTextInputLayout = view.findViewById(R.id.sort_order_text_input_layout_simple)
        sortOrderDropdown = view.findViewById(R.id.sort_order_dropdown_simple)
        filterCriterionChooser = view.findViewById(R.id.filter_criterion_chooser)
        individualSpeakerCategorySeriesChooser =
            view.findViewById(R.id.individual_speaker_category_series_chooser_text_input_layout)
        individualSpeakerCategorySeriesChooserAutoCompleteTextView =
            view.findViewById(R.id.individual_speaker_category_series_chooser_auto_complete_text_view)


        //</editor-fold>

        setAdapterAndSetHints(
            filterCriterionChooser,
            listOfFilterCriterion
        )

        //<editor-fold desc="listeners">
        progressiveFilterExplanationImageButton.setOnClickListener {
            val alertDialog: AlertDialog? =
                context?.let { it1 -> AlertDialog.Builder(it1).create() }
            alertDialog?.setTitle("Progressive Filtering")
            alertDialog?.setMessage("")
            alertDialog?.setButton(
                AlertDialog.BUTTON_NEUTRAL, "OK"
            ) { dialog, _ -> dialog.dismiss() }
            alertDialog?.setCanceledOnTouchOutside(true)
            alertDialog?.show()
        }
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
            val speakerString = resources.getString(TabType.SPEAKER.nameId)
            val categoryString = resources.getString(TabType.CATEGORY.nameId)
            val seriesString = resources.getString(TabType.SERIES.nameId)*/
            val (appropriateList, tabType) = when (position) {
                0 -> listOf(
                    listOfSpeakerNames,
                    resources.getString(TabType.SPEAKER.nameId)
                ) //calls to getString() are good for DRY and localization
                1 -> listOf(listOfCategoryNames, resources.getString(TabType.CATEGORY.nameId))
                2 -> listOf(listOfSeriesNames, resources.getString(TabType.SERIES.nameId))
                else -> listOf(listOf<String>(), "")
            }
            tabTypeBeingDisplayed = TabType.valueOf((tabType as String).toUpperCase(Locale.ROOT))
            if(selectedListItem!="") individualSpeakerCategorySeriesChooserAutoCompleteTextView.setText("")
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
            if (::tabTypeBeingDisplayed.isInitialized || ::fastScrollerDialogListItems.isInitialized) ChooserFastScrollerDialog( // would use &&, but if one is true, both are true, and && will always check both, but || will only check the second if the first is false, and I think that if the first is false, the second must also be false, but I am not 100% sure.
                fastScrollerDialogListItems
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
            //send back data to PARENT fragment using callback
            torahFilterableCallback.callbackFilter(tabTypeBeingDisplayed, selectedListItem)
            // Now dismiss the fragment
            dismiss()
        }
        cancelButton.setOnClickListener {
            // Dismiss the fragment
            dismiss()
        }
        resetButton.setOnClickListener {
            //send back data to PARENT fragment using callback
            torahFilterableCallback.callbackFilter(TabType.ALL, "")
            // Now dismiss the fragment
            dismiss()
        }
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
        tabTypeBeingDisplayed = TabType.valueOf(firstCondition.toUpperCase(Locale.ROOT))
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

class ChooserFastScrollerDialog(private val listItems: List<String>) :
    DialogFragment() {
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var toolbar: Toolbar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = true
        setHasOptionsMenu(true)
        val view: View =
            inflater.inflate(R.layout.fast_scroll_recycler_dialog_layout, container, false)
        selectedListItemTextView = view.findViewById(R.id.selected_list_item)
        toolbar = view.findViewById(R.id.custom_dialog_layout_toolbar)
        fastScrollerCancelButton = view.findViewById(R.id.fast_scroller_cancel_button)
        fastScrollerDeselectButton = view.findViewById(R.id.fast_scroller_deselect_button)
        fastScrollerSelectButton = view.findViewById(R.id.fast_scroller_select_button)

        toolbar.inflateMenu(R.menu.speaker_page_menu)
        toolbar.title = resources.getString(tabTypeBeingDisplayed.nameId)

        val menu: Menu = toolbar.menu
        //<editor-fold desc="SearchView decleration">
        val searchView = menu.findItem(R.id.actionSearch).actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                itemAdapter.filter(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "Search text changed.")
                itemAdapter.filter(newText ?: "")
                return false
            }
        })
        //</editor-fold>
        fastScrollerCancelButton.setOnClickListener {
            dismiss()
        }
        fastScrollerSelectButton.setOnClickListener {
            individualSpeakerCategorySeriesChooserAutoCompleteTextView.setText(selectedListItem)
            dismiss()
        }
        fastScrollerDeselectButton.setOnClickListener {
            selectedListItemTextView.text = ""
            selectedListItem = ""
            fastScrollerDeselectButton.isEnabled = false
            fastScrollerSelectButton.isEnabled = false
        }
        return view
    }

    override fun getTheme(): Int = R.style.DialogTheme


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: FastScrollView? = view.findViewById(R.id.fast_scroller)
        recyclerView?.setLayoutManager(LinearLayoutManager(context))

        fastScrollerCancelButton = view.findViewById(R.id.fast_scroller_cancel_button)
        fastScrollerDeselectButton = view.findViewById(R.id.fast_scroller_deselect_button)
        fastScrollerSelectButton = view.findViewById(R.id.fast_scroller_select_button)

        itemAdapter = ItemAdapter(listItems)
        recyclerView?.setAdapter(itemAdapter)

    }

    class ItemAdapter(private val listItems: List<String>) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>(), FastScroller.SectionIndexer {
        val tempListItems = listItems.toMutableList()

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: MaterialTextView = view.findViewById(R.id.text_view)

            init {

                view.setOnClickListener {

                    Log.d("", "Element $adapterPosition clicked.")
                    fastScrollerDeselectButton.isEnabled = true
                    fastScrollerSelectButton.isEnabled = true
                    tempListItems[adapterPosition].let {
                        selectedListItemTextView.text = it
                            selectedListItem = it
                    }
                }
            }
        }

        override fun getSectionText(position: Int): CharSequence  {
            val s = tempListItems[position]
            return s[s.lastIndexOf(' ')+1].toString()/*if (s.contains("Rabbi"))
                s.substring(s.indexOf(" ") + 1).first().toUpperCase().toString()
            else s.first().toUpperCase().toString()*/
        }


        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.simple_grey_text_view, viewGroup, false)
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            Log.d("", "Element $position set.")

            viewHolder.textView.text = tempListItems[position]
        }

        override fun getItemCount(): Int = tempListItems.size
        fun filter(constraint: String) {
            Log.d(TAG, "Filter ran")
/*
        var completeListIndex = 0
        var filteredListIndex = 0
        while (completeListIndex < originalSpeakerList.size) {
            val speaker: Speaker = originalSpeakerList[completeListIndex]
            if (speaker.name.toLowerCase(Locale.ROOT).trim().contains(constraint)) {
                if (filteredListIndex < speakerList.size) {
                    val filter: Speaker = speakerList[filteredListIndex]
                    if (speaker.name != filter.name) {
                        speakerList.add(filteredListIndex, speaker)
                        notifyItemInserted(filteredListIndex)
                    }
                } else {
                    speakerList.add(filteredListIndex, speaker)
                    notifyItemInserted(filteredListIndex)
                }
                filteredListIndex++
            } else if (filteredListIndex < speakerList.size) {
                val filter: Speaker = speakerList[filteredListIndex]
                if (speaker.name==filter.name) {
                    speakerList.removeAt(filteredListIndex)
                    notifyItemRemoved(filteredListIndex)
                }
            }
            completeListIndex++
        }
*/
            tempListItems.clear()
            if (constraint.isEmpty()) {
                tempListItems.addAll(listItems)
            } else {
                val filterPattern = constraint.toLowerCase(Locale.ROOT).trim()
                for (listItem in listItems) {
                    if (listItem.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                        tempListItems.add(listItem)
                    }
                }
            }
            notifyDataSetChanged()
        }

    }
}
