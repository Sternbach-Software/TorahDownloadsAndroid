package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.adapters.categoryAdapter.CategoryAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CONSTANTS
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Category
private const val TAG = "SubcategoriesPageActivity"
class SubcategoriesPageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)
        val listOfCategories = intent.extras?.getParcelableArrayList<Category>(CONSTANTS.EXTRA_CATEGORY_DETAILS)
        Log.d(TAG,"List of categories = $listOfCategories")
//        val subCategory4 = listOfInformation?.get(1)
//        val subCategory5 = listOfInformation?.get(1)
        val recyclerView: RecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = GridLayoutManager(this,3)
        recyclerView?.adapter = listOfCategories?.let { CategoryAdapter(it) }
    }
}