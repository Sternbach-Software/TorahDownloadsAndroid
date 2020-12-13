package tech.torah.aldis.androidapp.dataClassesAndInterfaces

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClientV1 {
    @GET("/s-{id}.json")
    fun getSpeaker(@Path("id") id: Int): Call<Speaker> // `Speaker` is the data class that represents a speaker

// ... similar for all other API calls
}