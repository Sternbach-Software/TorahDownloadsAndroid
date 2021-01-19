package tech.torah.aldis.androidapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tech.torah.aldis.androidapp.PageViewModel
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ShiurFilterOption
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur

class PlaceholderFragment(shiurim: List<Shiur>, moreFromThis: Boolean) : Fragment() {
 private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            val shiurFilterOption: ShiurFilterOption = when(arguments?.getInt(ARG_SECTION_NUMBER) ?: 0){
                0 -> ShiurFilterOption.NONE
                1 -> ShiurFilterOption.CATEGORY
                2 -> ShiurFilterOption.SPEAKER
                else -> ShiurFilterOption.SERIES
            }
//            setTabTypeAndTorahFilter(tabType,)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.plain_recycler_view_layout, container, false)

/*val recyclerView: Recycler = root.findViewById(R.id.section_label)
        pageViewModel.filteredShiurim.observe(this, Observer<String> {
            textView.text = it
        })*/
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, shiurim: List<Shiur>, moreFromThis:Boolean): PlaceholderFragment {
            return PlaceholderFragment(shiurim, moreFromThis).apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
/*
class TabFragment : Fragment() {
    // When requested, this adapter returns a TabObjectFragment,
    // representing an object in the collection.
    private lateinit var tabAdapter: TabAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.plain_recycler_view_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = view.findViewById<TabLayout>(R.id.tabs )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
      */
/*  tabAdapter = TabAdapter(this)
        viewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = tabAdapter*/
/*

    }
}

class TabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 100

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = TabObjectFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}

const val ARG_OBJECT = "object"

// Instances of this class are fragments representing a single
// object in our collection.
class TabObjectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.plain_recycler_view_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val textView: TextView = view.findViewById(android.R.id.text1)
            textView.text = getInt(ARG_OBJECT).toString()
        }
    }
}*/
