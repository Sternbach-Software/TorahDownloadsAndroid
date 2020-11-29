package tech.torah.aldis.androidapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
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
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CallbackListener
import java.util.*


/**
 * This is the dialog that will be used for filtering the Downloads, Favorites, and History pages
 * */
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

        //<editor-fold variable declerations>
        val filterButton = view.findViewById<MaterialButton>(R.id.filter_button_simple)
        val cancelButton = view.findViewById<MaterialButton>(R.id.cancel_button_simple)
        val filterRadioButton = view.findViewById<RadioButton>(R.id.filter_radio_button_simple)
        val sortRadioButton = view.findViewById<RadioButton>(R.id.sort_radio_button_simple)
        val sortOrderTextInputLayout =
            view.findViewById<TextInputLayout>(R.id.sort_order_text_input_layout_simple)
        val sortOrderDropdown =
            view.findViewById<AutoCompleteTextView>(R.id.sort_order_dropdown_simple)
        val filterCriterionChooser =
            view.findViewById<AutoCompleteTextView>(R.id.filter_criterion_chooser)
        val individualSpeakerCategorySeriesChooser =
            view.findViewById<TextInputLayout>(R.id.individual_speaker_category_series_chooser_text_input_layout)

        val listOfFilterCriterion = listOf("Speaker", "Category", "Series")
        val listOfAvailableSpeakers = listOf(
            "Rabbi Aaron Lopianskynnnnnnnnnnnn",
            "Rabbi Gedaliah Anemer",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "ha",
            "hb",
            "hg",
            "hz",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "ha",
            "hb",
            "hg",
            "hz",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "ha",
            "hb",
            "hg",
            "hz",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "ha",
            "hb",
            "hg",
            "hz",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "ha",
            "hb",
            "hg",
            "hz",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "ha",
            "hb",
            "hg",
            "hz",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "ha",
            "hb",
            "hg",
            "hz",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f",
            "g",
            "h",
            "ha",
            "hb",
            "hg",
            "hz"
        )
        val listOfAvailableCategories = listOf("Kil'ayim", "Mikvaos", "a")
        val listOfAvailableSeries = listOf("Amud Yomi", "Daf Yomi", "a")
        //</editor-fold>

        setAdapterAndSetHints(
            filterCriterionChooser,
            listOfFilterCriterion
        ) //TODO somehow also change the hint for individualSpeakerCategorySeriesTextInputLayout (hence "...Hint*s*(...)". Possibly create a Map<speakerCategorySeries :String, listOfAvailable... :List<String>>

//<editor-fold listeners>
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
            val (appropriateList, hint) = when (position) {
                0 -> listOf(listOfAvailableSpeakers, "Speaker")
                1 -> listOf(listOfAvailableCategories, "Category")
                2 -> listOf(listOfAvailableSeries, "Series")
                else -> listOf(listOf<String>(), "")
            }
            //TODO populate speaker/category/series popup with appropriate data
        }
        individualSpeakerCategorySeriesChooser.setOnClickListener {
            Log.d("TorahDOwnloadsAndroid", "individualSpeakerCategorySeriesChooser clicked")


            val randomWord = { length:Int ->
                val allowedChars = ('A'..'Z') + ('a'..'z')
                (1..length)
                    .map { allowedChars.random() }
                    .joinToString("")
            }
            val randomListOfWords = mutableListOf<String>()
            for (i in 1..500) randomListOfWords.add(randomWord(5))


            val listItems = randomListOfWords.sorted()
            ChooserFastScrollerDialog(listItems).show(
                childFragmentManager,
                "TorahDownloadsAndroid"
            )
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
        //</editor-fold>
    }

    //<editor-fold set hint and adapter functions>
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

        toolbar = view.findViewById(R.id.custom_dialog_layout_toolbar)

        toolbar.inflateMenu(R.menu.speaker_page_menu)

        val menu: Menu = toolbar.menu
        val searchView = menu.findItem(R.id.actionSearch).actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                itemAdapter.filter(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("TorahDownloadsAndroid", "Search text changed.")
                itemAdapter.filter(newText ?: "")
                return false
            }
        })
        return view
    }

    override fun getTheme(): Int = R.style.DialogTheme


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: FastScrollView? = view.findViewById(R.id.fast_scroller)
        recyclerView?.setLayoutManager(LinearLayoutManager(context))



        itemAdapter = ItemAdapter(listItems)
        recyclerView?.setAdapter(itemAdapter)

    }

/*    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.speaker_page_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                itemAdapter.filter(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                itemAdapter.filter(newText ?: "")
                return false
            }
        })
    }*/

    class ItemAdapter(private val listItems: List<String>) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>(), FastScroller.SectionIndexer {
        private val tempListItems = listItems.toMutableList()

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val textView: MaterialTextView

            init {
                v.setOnClickListener { Log.d("", "Element $adapterPosition clicked.") }
                textView = v.findViewById(R.id.text_view)
            }
        }

        override fun getSectionText(position: Int): CharSequence =
            tempListItems[position].first().toUpperCase().toString()


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
            Log.d("TorahDownloadsAndroid", "Filter ran")
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
