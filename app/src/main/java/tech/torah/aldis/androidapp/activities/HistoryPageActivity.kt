package tech.torah.aldis.androidapp.activities

import tech.torah.aldis.androidapp.R

class HistoryPageActivity : BaseShiurimPageActivity() {
    override val TAG = "HistoryPageActivity"
    override var pageTitle = "History" //getString(R.string.history) was causing java.lang.NullPointerException: Attempt to invoke virtual method 'android.content.res.Resources android.content.Context.getResources()' on a null object reference
}