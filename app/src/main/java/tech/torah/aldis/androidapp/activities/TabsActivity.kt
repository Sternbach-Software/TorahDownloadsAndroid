package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilterable
import tech.torah.aldis.androidapp.dialogs.ShiurimSortOrFilterDialog

private const val TAG = "TabsActivity"

class TabsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plain_recycler_view_layout)
    }

}