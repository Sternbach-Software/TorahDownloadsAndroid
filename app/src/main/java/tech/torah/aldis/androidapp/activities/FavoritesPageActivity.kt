package tech.torah.aldis.androidapp.activities

import tech.torah.aldis.androidapp.R

class FavoritesPageActivity : BaseShiurimPageActivity() {
    override val TAG = "FavoritesPageActivity"
    override var pageTitle = "Favorites" //getString(R.string.favorites) //was causing java.lang.NullPointerException: Attempt to invoke virtual method 'android.content.res.Resources android.content.Context.getResources()' on a null object reference
}
