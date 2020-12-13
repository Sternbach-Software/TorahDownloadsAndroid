package tech.torah.aldis.androidapp

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.ApiClientV1
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.CONSTANTS
import okhttp3.MediaType.Companion.toMediaType
/*
class TorahDownloadsApplication: Application() {
   @ExperimentalSerializationApi
   override fun onCreate() {
      super.onCreate()
      val retrofit = Retrofit.Builder()
         .addConverterFactory(Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
         }.asConverterFactory("application/json".toMediaType()))
         .baseUrl(CONSTANTS.serverUrl)
         .build()
      val apiClient = retrofit.create(ApiClientV1::class.java)
      apiClient.getSpeaker()
   }
}*/
