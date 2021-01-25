package tech.torah.aldis.androidapp.activities

//import tech.torah.aldis.androidapp.R


class RecentlyAddedShiurimPageActivity: BaseShiurimPageActivity() {
    override val TAG = "RecentlyAddedShiurimPageActivity"
    override var pageTitle = "Recently Added Shiurim" // getString(R.string.recently_added_shiurim) //was causing java.lang.NullPointerException: Attempt to invoke virtual method 'android.content.res.Resources android.content.Context.getResources()' on a null object reference
}
