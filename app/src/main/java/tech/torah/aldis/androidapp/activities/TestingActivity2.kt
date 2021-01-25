package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.textview.MaterialTextView
import tech.torah.aldis.androidapp.R


/*For testing with ViewPager2*/
private const val TAG = "TabsActivityTest"
private var listOfSelected = mutableListOf("AAA", "BBB", "CCC")
private var listOfDeSelected = mutableListOf("111","222","333")
private var tempList = mutableListOf<String>()
private var listToDisplay = 0
private lateinit var rv1: RecyclerView
private var rv1Adapter= ParallelNestedScrollingActivity.RvAdapter(listOfSelected)
private lateinit var rv2: RecyclerView
private var rv2Adapter = ParallelNestedScrollingActivity.RvAdapter(listOfDeSelected)
private var otherRecyclerViewNeedsUpdating = false
class ParallelNestedScrollingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate ran")
        val viewPager = ViewPager2(this).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            orientation = ORIENTATION_HORIZONTAL
            adapter = VpAdapter()
            val callback = object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d(TAG,"onPageSelected ran")
                    Log.d(TAG,"otherRecyclerViewNeedsUpdating = $otherRecyclerViewNeedsUpdating")
                    listToDisplay = position
                    Log.d(TAG,"listToDisplay = $listToDisplay")

                    //this is the only function (that i know of) that is called every time the user swipes
                    if(otherRecyclerViewNeedsUpdating){
                        //update the
                        if(listToDisplay==0) rv2Adapter.notifyDataSetChanged()
                        else if(listToDisplay==1) rv1Adapter.notifyDataSetChanged()
                        otherRecyclerViewNeedsUpdating = false //rv updated
                    }
                }

             /*   override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }*/
            /*

                override fun onPageScrollStateChanged(newState: Int) {
                    if (newState == ViewPager2.SCROLL_STATE_IDLE) {
                        updateCurrentItem()
                    }
                }*/
            }

            registerOnPageChangeCallback(callback)
        }
        setContentView(viewPager)
    }

    class VpAdapter : RecyclerView.Adapter<VpAdapter.VpViewHolder>() {
        override fun getItemCount(): Int {
            Log.d(TAG, "VpAdapter.getItemCount called")
            return 2 //create 20 pages/tabs for testing
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VpViewHolder {
            Log.d(TAG, "VpAdapter.onCreateViewHolder called")
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val root = inflater.inflate(R.layout.item_nested_recyclerviews, parent, false)
            return VpViewHolder(root).apply {
//                rv1.setUpRecyclerView(RecyclerView.HORIZONTAL)
                    rv1.layoutManager = LinearLayoutManager(context)
                    rv1.adapter = rv1Adapter

                if(::rv2.isInitialized){
                    Log.d(TAG, "Rv2 initialized")

                    rv2.layoutManager = LinearLayoutManager(context)
                    rv2.adapter = rv2Adapter}
            }
        }

        override fun onBindViewHolder(holderVp: VpViewHolder, position: Int) {
            Log.d(TAG,"VpAdapter.onBindViewHolder ran")
//            with(holderVp) {
//           sets title of page
            //                title.text = title.context.getString(R.string.page_position, adapterPosition)
//                itemView.setBackgroundResource(PAGE_COLORS[position % PAGE_COLORS.size])
//            }
        }

        class VpViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            init{
                Log.d(TAG,"VpViewHolder init ran")


                val findViewById = itemView.findViewById<RecyclerView>(R.id.second_rv)
                if(listToDisplay == 0) rv1 = findViewById
                else rv2 = findViewById
                if(::rv2.isInitialized){
                    Log.d(TAG, "Rv2 initialized")

                    rv2.layoutManager = LinearLayoutManager(itemView.context)
                    rv2.adapter = rv2Adapter}
            }
            //            val title: TextView = itemView.findViewById(R.id.page_title)
//            val rv1: RecyclerView = itemView.findViewById(R.id.first_rv)

        }
    }

    class RvAdapter(val list: List<String>) : RecyclerView.Adapter<RvAdapter.RvViewHolder>() {
        override fun getItemCount(): Int {
            Log.d(TAG,"RvAdapter.getItemCount ran")

            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
            Log.d(TAG,"RvAdapter.onCreateViewHolder ran")

            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.selected_filter_option_card_view, parent, false)
            return RvViewHolder(v)
        }

        override fun onBindViewHolder(holderRv: RvViewHolder, position: Int) {
            Log.d(TAG,"RvAdapter.onBindViewHolder ran")
            holderRv.bindItem(list[position])
        }

      inner class RvViewHolder(private val tv: View) : RecyclerView.ViewHolder(tv){
            fun bindItem(text: String){

                val textView = tv.findViewById<MaterialTextView>(R.id.text_view)
                textView.text = text
                textView.setOnClickListener{Log.d(TAG, "TextView clicked")
                    var textWasInDeselected = text in listOfDeSelected
                if(textWasInDeselected) {
                    listOfDeSelected.remove(text)
                    listOfSelected.add(text)

                } else if (text in listOfSelected){
                    listOfDeSelected.add(text)
                    listOfSelected.remove(text)
                }
                    notifyDataSetChanged()
                    otherRecyclerViewNeedsUpdating = true
//                    vpAdapter.notifyDataSetChanged()
                    Log.d(TAG, "List of selected: $listOfSelected")
                    Log.d(TAG, "List of deselected: $listOfDeSelected")
                }
            }
        }
    }
}