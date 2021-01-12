package tech.torah.aldis.androidapp.adapters.categoryAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.*

/*class CategoryAdapter:
    RecyclerView.Adapter<CategoryAdapter.ViewHolder.ViewHolder>(), FastScroller.SectionIndexer,
    TorahFilterable {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textView: TextView

        init {
            v.setOnClickListener { Log.d("", "Element $adapterPosition clicked.") }
            textView = v.findViewById(R.id.text_view)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getSectionText(position: Int): CharSequence {
        TODO("Not yet implemented")
    }

    override fun filter(constraint: String, tabType: TabType, exactMatch: Boolean) {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}*/
class CategoryAdapter(private val originalList: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>(), TorahFilterable, TorahAdapter {
    private val workingList = originalList.clone() as ArrayList<Category>
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val categoryName: TextView
        val subcategory1: TextView
        val subcategory2: TextView
        val subcategory3: TextView
//        val subcategory4: TextView
//        val subcategory5: TextView

        init {
            v.setOnClickListener { Log.d("", "Element $adapterPosition clicked.") }
            categoryName = v.findViewById(R.id.category_name)
            subcategory1 = v.findViewById(R.id.subcategory1)
            subcategory2 = v.findViewById(R.id.subcategory2)
            subcategory3 = v.findViewById(R.id.subcategory3)
//            subcategory4 = v.findViewById(R.id.subcategory4)
//            subcategory5 = v.findViewById(R.id.subcategory5)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.category_card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d("", "Element $position set.")

        val category = workingList[position]
        viewHolder.categoryName.text = category.name
        val children = category.children
        viewHolder.subcategory1.text = children?.get(0)?.name ?: ""
        viewHolder.subcategory2.text = children?.get(1)?.name ?: ""
        viewHolder.subcategory3.text = children?.get(2)?.name ?: ""
    }

    override fun getItemCount(): Int = workingList.size
    override fun filter(constraint: String, tabType: TabType, exactMatch: Boolean) {
        FunctionLibrary.filter(
            constraint,
            originalList,
            workingList,
            this,
            tabType,
            exactMatch
        )
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}