package tech.torah.aldis.androidapp

import android.app.Application
import android.os.Process
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.serialization.ExperimentalSerializationApi
private const val TAG = "TorahDownloadsApplication"
class TorahDownloadsApplication: Application() {
   @ExperimentalSerializationApi
   override fun onCreate() {
      super.onCreate()
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
