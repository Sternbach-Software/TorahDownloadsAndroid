package tech.torah.aldis.androidapp.activities.drag_example

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener
import com.michaelflisar.dragselectrecyclerview.DragSelectionProcessor
import com.michaelflisar.dragselectrecyclerview.DragSelectionProcessor.ISelectionHandler
import tech.torah.aldis.androidapp.R
import java.util.*

//public class SampleDragSelectionActivity {
//}
class SampleDragSelectionActivity : AppCompatActivity() {
    private val mMode = DragSelectionProcessor.Mode.FirstItemDependent
    private var mToolbar: Toolbar? = null
    private var mAdapter: TestAutoDataAdapter? = null
    private var mDragSelectionProcessor: DragSelectionProcessor? = null
    private lateinit var mDragSelectTouchListener: DragSelectTouchListener //would be initialized the same as the others, but "rvData.addOnItemTouchListener(mDragSelectTouchListener) threw a race condition."
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.barren_recycler_view)
        mToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        mToolbar!!.title = "DragSelectRecyclerView"
        setSupportActionBar(mToolbar)

        // 1) Prepare the RecyclerView (init LayoutManager and set Adapter)
        val rvData = findViewById<View>(R.id.recycler_view) as RecyclerView
        val glm = GridLayoutManager(this, 3)
        rvData.layoutManager = glm
        mAdapter = TestAutoDataAdapter(this, 500, mDragSelectTouchListener)
        rvData.adapter = mAdapter

        // 2) Add the DragSelectListener
        mDragSelectionProcessor = DragSelectionProcessor(object : ISelectionHandler {
            override fun getSelection(): HashSet<Int> {
                return mAdapter!!.selection
            }

            override fun isSelected(index: Int): Boolean {
                return mAdapter!!.selection.contains(index)
            }

            override fun updateSelection(
                start: Int,
                end: Int,
                isSelected: Boolean,
                calledFromOnStart: Boolean
            ) {
                mAdapter!!.selectRange(start, end, isSelected)
            }
        }).withMode(mMode)
        mDragSelectTouchListener = DragSelectTouchListener().withSelectListener(mDragSelectionProcessor)
        mDragSelectionProcessor!!.withMode(mMode) //TODO not sure if this is superfluous
        rvData.addOnItemTouchListener(mDragSelectTouchListener)
    }
}