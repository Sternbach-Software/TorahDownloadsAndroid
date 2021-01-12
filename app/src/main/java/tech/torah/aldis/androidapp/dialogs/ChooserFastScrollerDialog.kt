package tech.torah.aldis.androidapp.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.l4digital.fastscroll.FastScrollView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CallbackListener
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
    private val callbackListener: CallbackListener
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

        toolbar.inflateMenu(R.menu.search_bar_only)
        toolbar.title = resources.getString(tabTypeBeingDisplayed.nameId)


        fastScrollerCancelButton.setOnClickListener {
            dismiss()
        }
        fastScrollerSelectButton.setOnClickListener {
            callbackListener.onDataReceived(selectedListItem)
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
        FunctionLibrary.setupSearchView(toolbar.menu,chooserFastScrollerAdapter) //this was in
        // onCreateView, but chooserFastScrollerAdapter had not yet been initialized

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
                        Log.d(TAG, "workingList[adapterPosition] = $it")
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

        override fun filter(constraint: String, tabType: TabType, exactMatch: Boolean) =
            FunctionLibrary.filter(
                constraint,
                originalList,
                workingList,
                this,
                tabType,
                exactMatch
            )

        override fun reset() = FunctionLibrary.reset(originalList,workingList,this)

    }

}