package tech.torah.aldis.androidapp.adapters.categoryAdapter

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.activities.BaseShiurimPageActivity
import tech.torah.aldis.androidapp.activities.SubcategoriesPageActivity
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

    override fun filter(constraint: String, tabType: ShiurFilterOption, exactMatch: Boolean) {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}*/
private const val TAG = "CategoryAdapter"
class CategoryAdapter(private val originalList: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>(), TorahFilterable, TorahAdapter {
    private val workingList = originalList.clone() as ArrayList<Category>

    @RequiresApi(Build.VERSION_CODES.M)
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val categoryName: TextView
        val subcategory1: TextView
        val subcategory2: TextView
        val subcategory3: TextView
//        val subcategory4: TextView
//        val subcategory5: TextView

        init {
            Log.d(TAG,"Init ran ${v.accessibilityClassName}")
            v.setOnClickListener {
                val buttonCategory = workingList[adapterPosition]
                Log.d(TAG,"Button category = $buttonCategory")
                val context = it.context
                val hasChildren = buttonCategory.hasChildren
                val intent =  Intent(context, if (hasChildren) SubcategoriesPageActivity::class.java else BaseShiurimPageActivity::class.java).apply {
                    if (hasChildren) putParcelableArrayListExtra(CONSTANTS.INTENT_EXTRA_CATEGORY_DETAILS,buttonCategory.children)
                    else putParcelableArrayListExtra(CONSTANTS.INTENT_EXTRA_CATEGORY_CHILD_SHIURIM,getChildShiurim(buttonCategory))
                    putExtra(CONSTANTS.INTENT_EXTRA_SUBCATEGORY_PARENT_NAME,buttonCategory.name)
                }
                context.startActivity(intent)
            }
            categoryName = v.findViewById(R.id.category_name)
            subcategory1 = v.findViewById(R.id.subcategory1)
            subcategory2 = v.findViewById(R.id.subcategory2)
            subcategory3 = v.findViewById(R.id.subcategory3)
//            subcategory4 = v.findViewById(R.id.subcategory4)
//            subcategory5 = v.findViewById(R.id.subcategory5)
        }
    }

    private fun getChildShiurim(buttonCategory: Category): ArrayList<out Parcelable> {
        return CONSTANTS.sampleListOfShiurim
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.category_card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d(TAG, "Element $position set.")

        val category = workingList[position]
        viewHolder.categoryName.text = category.name
        val children = category.children
        fun hasAtLeast(numElements: Int) = children?.size ?: -1 >= numElements
        if (hasAtLeast(1)) viewHolder.subcategory1.text = children?.get(0)?.name ?: ""
        if (hasAtLeast(2)) viewHolder.subcategory2.text = children?.get(1)?.name ?: ""
        if (hasAtLeast(3)) viewHolder.subcategory3.text = children?.get(2)?.name ?: ""
    }

    override fun getItemCount(): Int = workingList.size
    override fun filter(constraint: String, shiurFilterOption: ShiurFilterOption, exactMatch: Boolean) {
        FunctionLibrary.filter(
            constraint,
            originalList,
            workingList,
            this,
            shiurFilterOption,
            exactMatch
        )
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}