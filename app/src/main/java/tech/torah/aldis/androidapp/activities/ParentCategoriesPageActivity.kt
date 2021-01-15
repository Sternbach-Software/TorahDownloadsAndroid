package tech.torah.aldis.androidapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CONSTANTS
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CONSTANTS.INTENT_EXTRA_SUBCATEGORY_PARENT_NAME
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.Category


private const val TAG = "ParentCategoriesPageActivity"
private lateinit var parentLinearLayout: LinearLayout

class ParentCategoriesPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categories_page_top_level_scroll_view)
        val categories = arrayListOf(
            Category(
                name = "Chumash",
                children = arrayListOf(
                    Category(
                        name = "Sefer Bereishis", children = arrayListOf(
                            Category(name = "Bereishis"),
                            Category(name = "Noach"),
                            Category(name = "Lech Lecha"),
                            Category(name = "Vayeira"),
                            Category(name = "Chayei Sarah")
                        )
                    ),
                    Category(
                        name = "Sefer Shemos", children = arrayListOf(
                            Category(name = "Shemos"),
                            Category(name = "Va'eira"),
                            Category(name = "Bo"),
                            Category(name = "Beshalach"),
                        )
                    ),
                    Category(
                        name = "Sefer Vayikra", children = arrayListOf(
                            Category(name = "Vayikra"),
                            Category(name = "Tzav"),
                            Category(name = "Shemini"),
                            Category(name = "Tazria"),
                        )
                    ),
                    Category(
                        name = "Sefer Bamidbar", children = arrayListOf(
                            Category(name = "Bamidbar"),
                            Category(name = "Naso"),
                            Category(name = "Beha'aloscha"),
                        )
                    ),
                    Category(name = "Sefer Devarim")
                )
            ),
            Category(
                name = "Nach",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            ),
            Category(
                name = "Talmud",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            ),
            Category(
                name = "Mishnah",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            ),
            Category(
                name = "Halacha",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            ),
            Category(
                name = "Moadim",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            ),
            Category(
                name = "Machshava",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            ),
            Category(
                name = "Family",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            ),
            Category(
                name = "Orech ChaimOrech ChaimOrech ChaimOrech ChaimOr",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            ),
            Category(
                name = "Leining",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            ),
            Category(
                name = "History",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            ),
            Category(
                name = "Sifrei Kodesh",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            ),
            Category(
                name = "Odd one out",
                children = arrayListOf(
                    Category(name = "Category A"),
                    Category(name = "Category B"),
                    Category(name = "Category C"),
                    Category(name = "Category D"),
                    Category(name = "Category E")
                )
            )
        )
        populatePageWithButtons(categories)
    }

    private fun populatePageWithButtons(categories: ArrayList<Category>) {
        var linearLayout = newPreconfiguredLinearLayout()
        parentLinearLayout = findViewById(R.id.parent_linear_layout)
        parentLinearLayout.addView(linearLayout)

        var numberOfChildrenInChildLinearLayout = 0
        for (index in categories.indices) {
            val currentCategory = categories[index]
            val currentCategoryName = currentCategory.name
            val button = MaterialButton(this)

            button.text = currentCategoryName
            button.tag = currentCategory
            if (numberOfChildrenInChildLinearLayout == 2) {

                val pair = createRightButton(index, categories, button)
                linearLayout = pair.first
                numberOfChildrenInChildLinearLayout = pair.second

            } else {

                numberOfChildrenInChildLinearLayout = createLeftButton(
                    index,
                    categories,
                    numberOfChildrenInChildLinearLayout,
                    button,
                    linearLayout
                )

            }

            button.setOnClickListener {
                val context = button.context
                val buttonCategory = button.tag as Category
                val intent = Intent(context, SubcategoriesPageActivity::class.java).apply {
                    putParcelableArrayListExtra(
                        CONSTANTS.INTENT_EXTRA_CATEGORY_DETAILS,
                        buttonCategory.children
                    )
                        putExtra(INTENT_EXTRA_SUBCATEGORY_PARENT_NAME,buttonCategory.name)
                }
                context.startActivity(intent)
            }
        }
    }

    private fun createLeftButton(
        index: Int,
        categories: ArrayList<Category>,
        numberOfChildrenInChildLinearLayout: Int,
        button: MaterialButton,
        linearLayout: LinearLayout
    ): Int {
        var numberOfChildrenInChildLinearLayout1 = numberOfChildrenInChildLinearLayout
        val layoutParams = LinearLayout.LayoutParams(getPXFromDP(130F), getPXFromDP(100F))
        if (index != categories.size - 1) {
            if (numberOfChildrenInChildLinearLayout1 == 0) layoutParams.marginEnd =
                getPXFromDP(40F)
            else layoutParams.marginStart = getPXFromDP(40F)
        }
        button.layoutParams = layoutParams
        linearLayout.addView(button)
        numberOfChildrenInChildLinearLayout1++
        return numberOfChildrenInChildLinearLayout1
    }

    private fun createRightButton(
        index: Int,
        categories: ArrayList<Category>,
        button: MaterialButton
    ): Pair<LinearLayout, Int> {
        val linearLayout1 = newPreconfiguredLinearLayout()
        var numberOfChildrenInChildLinearLayout1 = 0
        val layoutParams = LinearLayout.LayoutParams(getPXFromDP(130F), getPXFromDP(100F))
        if (index != categories.size - 1) layoutParams.marginEnd = getPXFromDP(40F)
        button.layoutParams = layoutParams
        parentLinearLayout.addView(linearLayout1)
        linearLayout1.addView(button)
        numberOfChildrenInChildLinearLayout1++
        return Pair(linearLayout1, numberOfChildrenInChildLinearLayout1)
    }

    fun getPXFromDP(yourdpmeasure: Float): Int = TypedValue
        .applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            yourdpmeasure,
            resources.displayMetrics
        ).toInt()


    private fun newPreconfiguredLinearLayout(): LinearLayout {
        val linearLayout = LinearLayout(this@ParentCategoriesPageActivity)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        linearLayout.setPadding(0, getPXFromDP(40F), 0, 0)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.gravity = Gravity.CENTER
//        params.setMargins(15, 5, 5, 5)
        linearLayout.layoutParams = params
        return linearLayout
    }
}