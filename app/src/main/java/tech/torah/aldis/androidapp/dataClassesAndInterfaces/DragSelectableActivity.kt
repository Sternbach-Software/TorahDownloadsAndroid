package tech.torah.aldis.androidapp.dataClassesAndInterfaces

import android.view.Menu
import androidx.core.content.ContextCompat
import tech.torah.aldis.androidapp.R
import tech.torah.aldis.androidapp.mEntireApplicationContext

/**
 * A class which implements this interface indicates that it contains items which can be
 * individually selected and drag selected. The implementing class is also expected to have some way
 * for the user to indicate that they would like to start a selection session, such as by clicking
 * a button in the toolbar to start the selection.
 * */
interface DragSelectableActivity {
    var dragSelectModeEnabled: Boolean
    var actionMenu: Menu?

    //    fun selectRange(start: Int, end: Int, selected: Boolean)
//    fun toggleSelection(pos: Int)
/*    fun getDragSelectModeEnabled():Boolean? {
        return dragSelectModeEnabled
    }
    fun setDragSelectModeEnabled(enabled:Boolean?){
        dragSelectModeEnabled = enabled!!
    }*/
    fun setSelectionIconToEnabled(enabled: Boolean) {
        val selectionButton = actionMenu?.findItem(R.id.start_selection_button)
        selectionButton?.icon = if (enabled) ContextCompat.getDrawable(
            mEntireApplicationContext,
            R.drawable.ic_cancel
        ) else ContextCompat.getDrawable(mEntireApplicationContext, R.drawable.ic_select_all)
    }
/**
 * Used to clear the selection of selected list items when the user clicks the cancel button
 * */
    fun clearSelection() //TODO refactor to take a TorahAdapter as a parameter and make the default
                         //implementation: torahAdapter.deselectAll()
}

/**
 * @return true if the user has started the selection session (the click listener is expected
 * to not react to a click by starting a drag session or toggling a selection if this function
 * returns false/the user has not indicated that they would like to start a selection session).
 * Made nullable to prevent JVM clash because getter and setter of [dragSelectModeEnabled] have same signature. Did not use
 * */
//    val selectionHandler: DragSelectionProcessor.ISelectionHandler
