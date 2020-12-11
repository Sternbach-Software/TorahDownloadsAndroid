package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import tech.torah.aldis.androidapp.R

private const val TAG = "CategoriesPageActivity"
private lateinit var parentLinearLayout:LinearLayout
class CategoriesPageActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categories_page_top_level_scroll_view)
        parentLinearLayout = findViewById(R.id.parent_linear_layout)
    }
}