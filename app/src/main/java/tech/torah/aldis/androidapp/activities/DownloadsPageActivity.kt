package tech.torah.aldis.androidapp.activities

//import tech.torah.aldis.androidapp.R


class DownloadsPageActivity : BaseShiurimPageActivity() {
    override val TAG = "DownloadsPageActivity"
    override var pageTitle = "Downloads" //getString(R.string.downloads) ////was causing java.lang.NullPointerException: Attempt to invoke virtual method 'android.content.res.Resources android.content.Context.getResources()' on a null object reference

}
