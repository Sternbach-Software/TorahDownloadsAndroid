package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.adapters.categoryAdapter.CategoryAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CONSTANTS
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Category
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.FunctionLibrary.setupShiurimButton

private const val TAG = "SubcategoriesPageActiv"
class SubcategoriesPageActivity: AppCompatActivity() {
    private var parentCategoryName: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)
        val listOfCategories = intent.extras?.getParcelableArrayList<Category>(CONSTANTS.INTENT_EXTRA_CATEGORY_DETAILS)
        parentCategoryName = intent.extras?.getString(CONSTANTS.INTENT_EXTRA_SUBCATEGORY_PARENT_NAME)
        Log.d(TAG,"List of categories = $listOfCategories")
//        val subCategory4 = listOfInformation?.get(1)
//        val subCategory5 = listOfInformation?.get(1)
        supportActionBar?.title = parentCategoryName

        val recyclerView: RecyclerView? = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = GridLayoutManager(this,3)
        recyclerView?.adapter = listOfCategories?.let { CategoryAdapter(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        setupShiurimButton(menu, menuInflater, this,parentCategoryName,CONSTANTS.sampleListOfShiurim)
        return true
    }
}