package tech.torah.aldis.androidapp.dataClassesAndInterfaces

import android.view.View

/**
 * If a class implements this, it means that it can hold shiur cards, and must implement [openOptionsMenu]
 *
 * */
interface HoldsShiurCard {
    fun openOptionsMenu(@Suppress("UNUSED_PARAMETER") v: View)
}