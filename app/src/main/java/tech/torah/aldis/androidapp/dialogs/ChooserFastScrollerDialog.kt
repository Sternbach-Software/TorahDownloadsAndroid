package tech.torah.aldis.androidapp.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.l4digital.fastscroll.FastScrollView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TabType
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable

private lateinit var selectedListItemTextView: MaterialTextView
private lateinit var fastScrollerSelectButton: MaterialButton
private lateinit var fastScrollerCancelButton: MaterialButton
private lateinit var fastScrollerDeselectButton: MaterialButton

private const val TAG = "ChooserFastScrollerDialog"
class ChooserFastScrollerDialog(
    private val listItems: List<String>,
    private val tabTypeBeingDisplayed: TabType,
    private val individualSpeakerCategorySeriesChooserAutoCompleteTextView: AutoCompleteTextView
) :
    DialogFragment() {
    private lateinit var chooserFastScrollerAdapter: ChooserFastScrollerAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var selectedListItem: String
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

       FunctionLibrary.setupSearchView(toolbar.menu,chooserFastScrollerAdapter)

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

        chooserFastScrollerAdapter = ChooserFastScrollerAdapter(listItems)
        recyclerView?.setAdapter(chooserFastScrollerAdapter)

    }

    inner class ChooserFastScrollerAdapter(private val originalList: List<String>) :
        RecyclerView.Adapter<ChooserFastScrollerAdapter.ViewHolder>(), FastScroller.SectionIndexer, TorahFilterable {
        val workingList = originalList.toMutableList()

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: MaterialTextView = view.findViewById(R.id.text_view)

            init {

                view.setOnClickListener {

                    Log.d(TAG, "Element $adapterPosition clicked.")
                    fastScrollerDeselectButton.isEnabled = true
                    fastScrollerSelectButton.isEnabled = true
                    workingList[adapterPosition].let {
                        selectedListItemTextView.text = it
                        selectedListItem = it
                    }
                }
            }
        }

        override fun getSectionText(position: Int): CharSequence {
            val s = workingList[position]
            return s[s.lastIndexOf(' ') + 1].toString()/*if (s.contains("Rabbi"))
                s.substring(s.indexOf(" ") + 1).first().toUpperCase().toString()
            else s.first().toUpperCase().toString()*/
        }


        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.simple_grey_text_view_card, viewGroup, false)
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            Log.d(TAG, "Element $position set.")

            viewHolder.textView.text = workingList[position]
        }

        override fun getItemCount(): Int = workingList.size

        override fun filter(constraint: String, tabType: TabType) = FunctionLibrary.filter(constraint, originalList, workingList, this, tabType = tabType)

        override fun reset() = FunctionLibrary.reset(originalList,workingList,this)

    }
}