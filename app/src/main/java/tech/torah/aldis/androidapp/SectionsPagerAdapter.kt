package tech.torah.aldis.androidapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.Shiur
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants.ShiurFullPage
import tech.torah.aldis.androidapp.fragments.MoreFromThisFragment
import tech.torah.aldis.androidapp.fragments.PlaceholderFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(
    private val context: Context,
    fm: FragmentManager,
    private val shiurim: ArrayList<Shiur>,
    private val moreFromThis: Boolean
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return if(moreFromThis) MoreFromThisFragment.newInstance(position,shiurim) else PlaceholderFragment.newInstance(position, shiurim, moreFromThis)
    }

    override fun getPageTitle(position: Int): CharSequence? =
        if (moreFromThis)

            when (position) {
            0 -> "Speaker"
            1 -> "Category"
            else -> "Series"
                            }

        else

            when (position) {
            0 -> "Shiurim"
            else -> "Filter"
                            }

    override fun getCount(): Int = if (moreFromThis) 3 else 2
        // If this is a "More from this..." page, show 3 total pages, otherwise show 2.
}