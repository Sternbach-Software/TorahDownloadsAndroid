package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.l4digital.fastscroll.FastScroller
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary

private const val TAG = "TestingActivity"

class TestingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)
        val recyclerView: RecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = GridLayoutManager(this,2)
        val listItems = listOf(
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
            "Condition",
        )
        val itemAdapter = ItemAdapter(listItems)
        recyclerView?.adapter = (itemAdapter)
    }

    class ItemAdapter(private val listItems: List<String>) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>(), FastScroller.SectionIndexer {
        private val tempListItems = listItems.toMutableList()
        private val LAYOUTS = listOf(
            R.layout.selected_filter_option_card_view,
            R.layout.deselected_filter_option_card_view
        )
        private var layoutIndex = 0

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: MaterialTextView = view.findViewById(R.id.text_view)

            init {

                view.setOnClickListener {

                    Log.d(TAG, "Element $adapterPosition clicked.")
                }
            }
        }

        override fun getSectionText(position: Int): CharSequence {
            val s = tempListItems[position]
            return s[s.lastIndexOf(' ') + 1].toString()/*if (s.contains("Rabbi"))
                s.substring(s.indexOf(" ") + 1).first().toUpperCase().toString()
            else s.first().toUpperCase().toString()*/
        }


        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                LayoutInflater.from(viewGroup.context)
                    .inflate(LAYOUTS[layoutIndex++ % 2], viewGroup, false)
            )

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            Log.d(TAG, "Element $position set.")

            viewHolder.textView.text = tempListItems[position]
        }

        override fun getItemCount(): Int = tempListItems.size
        fun filter(constraint: String) {
            FunctionLibrary.filter(
                constraint,
                listItems,
                tempListItems,
                this,
                exactMatch = false
            )
        }
    }
}