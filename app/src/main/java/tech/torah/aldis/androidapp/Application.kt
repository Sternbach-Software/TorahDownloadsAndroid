package tech.torah.aldis.androidapp

import android.app.Application
import android.content.Context

lateinit var mEntireApplicationContext: Context
private const val TAG = "TorahDownloadsApplication"

class TorahDownloadsApplication: Application() {
   override fun onCreate() {
      super.onCreate()
      mEntireApplicationContext = this
//      AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
//       Log.d(TAG, "$TAG.onCreate ran")

/*      val retrofit = Retrofit.Builder()
         .addConverterFactory(Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
         }.asConverterFactory("application/json".toMediaType()))
         .baseUrl(CONSTANTS.serverUrl)
         .build()
      val apiClient = retrofit.create(ApiClientV1::class.java)
      apiClient.getSpeaker()*/
   }
}
