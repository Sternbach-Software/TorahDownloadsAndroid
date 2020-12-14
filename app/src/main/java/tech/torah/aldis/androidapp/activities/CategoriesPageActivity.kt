package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import tech.torah.aldis.androidapp.R


private const val TAG = "CategoriesPageActivity"
private lateinit var parentLinearLayout: LinearLayout

class CategoriesPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categories_page_top_level_scroll_view)
        val categories = listOf(
            "Chumash",
            "Nach",
            "Talmud",
            "Mishnah",
            "Halacha",
            "Moadim",
            "Machshava",
            "Family",
            "Tefilla",
            "Leining",
            "History",
            "Sifrei Kodesh",
            "Odd one out"
        )
        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        var linearLayout = newPreconfiguredLinearLayout()
        parentLinearLayout.addView(linearLayout)

        var numberOfChildrenInChildLinearLayout = 0
        for (index in categories.indices) {
            if (numberOfChildrenInChildLinearLayout == 2) {
                linearLayout = newPreconfiguredLinearLayout()
                numberOfChildrenInChildLinearLayout=0
                val button = MaterialButton(this)
                button.text = categories[index]
                val layoutParams = LinearLayout.LayoutParams(getPXFromDP(130F), getPXFromDP(100F))
                if(index != categories.size-1) layoutParams.marginEnd = getPXFromDP(40F)
                button.layoutParams = layoutParams
//            btn.setOnClickListener(buttonClick)
                parentLinearLayout.addView(linearLayout)
                linearLayout.addView(button)
                numberOfChildrenInChildLinearLayout++
            } else {
                val button = MaterialButton(this)
                button.text = categories[index]
                val layoutParams = LinearLayout.LayoutParams(getPXFromDP(130F), getPXFromDP(100F))
                if(index != categories.size-1) {
                    if (numberOfChildrenInChildLinearLayout==0) layoutParams.marginEnd = getPXFromDP(40F)
                    else layoutParams.marginStart = getPXFromDP(40F)
                }
                button.layoutParams = layoutParams
//            btn.setOnClickListener(buttonClick)
                linearLayout.addView(button)
                numberOfChildrenInChildLinearLayout++
            }
        }
    }

    private fun getPXFromDP(yourdpmeasure: Float): Int = TypedValue
        .applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            yourdpmeasure,
            resources.displayMetrics
        ).toInt()


    private fun newPreconfiguredLinearLayout(): LinearLayout {
        val linearLayout = LinearLayout(this@CategoriesPageActivity)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        linearLayout.setPadding(0,getPXFromDP(40F),0,0)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.gravity = Gravity.CENTER
//        params.setMargins(15, 5, 5, 5)
        linearLayout.layoutParams = params
        return linearLayout
    }
}