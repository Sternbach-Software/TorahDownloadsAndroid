package tech.torah.aldis.androidapp.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textview.MaterialTextView
import tech.torah.aldis.androidapp.R


/*For testing with ViewPager2*/
private const val TAG = "TabsActivityTest"
private var listOfSelected = mutableListOf("AAA", "BBB", "CCC")
private var listOfDeSelected = mutableListOf("111","222","333")
private var tempList = mutableListOf<String>()
private var listToDisplay = 0
private lateinit var tabLayout: TabLayout
private lateinit var viewPager: ViewPager2
private lateinit var rv1: RecyclerView
private var rv1Adapter= ViewPagerOfRecyclerViews1.RvAdapter(listOfSelected)
private var rv2: RecyclerView? = null // can be null because rv1 is displayed first, and rv2 may not yet be drawn before the user updates its
private var rv2Adapter = ViewPagerOfRecyclerViews1.RvAdapter(listOfDeSelected)
private var otherRecyclerViewNeedsUpdating = false
/* Didn't work
class MainActivityTesting2 : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testing_layout)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)
        viewPager.adapter= ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position){
                0 -> "Selected"
                else -> "Deselected"
            }
        }.attach()
    }
}
class CardFragment(val position: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.plain_recycler_view_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val shiurAdapter = ItemAdapter(if(position == 0) listOfSelected else listOfDeSelected)
        recyclerView?.adapter = shiurAdapter
    }
    inner class ItemAdapter(private val listItems: List<String>) :
        RecyclerView.Adapter<ItemAdapter.VpViewHolder>() {
        private val tempListItems = listItems.toMutableList()

        inner class VpViewHolder(view: View) : RecyclerView.VpViewHolder(view) {
            val textView: MaterialTextView = view.findViewById(R.id.text_view)

            init {

                view.setOnClickListener {

                    Log.d(TAG, "Element $adapterPosition clicked.")
                }
            }
        }

    *//*    override fun getSectionText(position: Int): CharSequence {
            val s = tempListItems[position]
            return s[s.lastIndexOf(' ') + 1].toString()*//**/
/*if (s.contains("Rabbi"))
                s.substring(s.indexOf(" ") + 1).first().toUpperCase().toString()
            else s.first().toUpperCase().toString()*//**//*
        }*/
/*


        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): VpViewHolder =
            VpViewHolder(
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.selected_filter_option_card_view, viewGroup, false)
            )

        override fun onBindViewHolder(viewHolder: VpViewHolder, position: Int) {
            Log.d(TAG, "Element $position set.")

            viewHolder.textView.text = tempListItems[position]
        }

        override fun getItemCount(): Int = tempListItems.size
        fun filter(constraint: String) {
            FunctionLibrary.filter(constraint, listItems, tempListItems, this, exactMatch = false)
        }

    }
}
class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return CardFragment(position)
    }

    override fun getItemCount(): Int {
        return 2
    }
}*/
class ViewPagerOfRecyclerViews1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_pager_and_tab_layout_1)
        Log.d(TAG,"onCreate ran")
        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById<ViewPager2>(R.id.view_pager).apply {
            orientation = ORIENTATION_HORIZONTAL
            adapter = VpAdapter()
            val callback = object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d(TAG,"onPageSelected ran")
                    Log.d(TAG,"position = $position")
                    listToDisplay = position
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
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0) "Selected" else "Deselected"
        }.attach()
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
            val root = inflater.inflate(if (listToDisplay == 0) R.layout.pager_recycler_1 else R.layout.pager_recycler_2, parent, false)
            return VpViewHolder(root).apply {
//                rv1.setUpRecyclerView(RecyclerView.HORIZONTAL)
                    rv1.layoutManager = LinearLayoutManager(context)
                    rv1.adapter = rv1Adapter
                    rv2?.layoutManager = LinearLayoutManager(context)
                    rv2?.adapter = rv2Adapter
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
                rv1 = itemView.findViewById(R.id.pager_recycler_1)
                rv2 = itemView.findViewById(R.id.pager_recycler_2)
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
                .inflate(R.layout.simple_grey_text_view_card, parent, false)
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
                        //update the
                        /*if(listToDisplay==0)*/ rv2Adapter.notifyDataSetChanged()
                        /*else if(listToDisplay==1)*/ rv1Adapter.notifyDataSetChanged()
                        otherRecyclerViewNeedsUpdating = false //rv updated
//                    notifyDataSetChanged() //TODO improve this to incude specific for removeItem and insertItem
//                    otherRecyclerViewNeedsUpdating = true
//                    vpAdapter.notifyDataSetChanged()
                    Log.d(TAG, "List of selected: $listOfSelected")
                    Log.d(TAG, "List of deselected: $listOfDeSelected")
                }
            }
        }
    }
}