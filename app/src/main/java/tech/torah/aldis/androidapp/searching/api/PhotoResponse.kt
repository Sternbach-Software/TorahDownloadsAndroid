package tech.torah.aldis.androidapp.searching.api

import com.google.gson.annotations.SerializedName
import tech.torah.aldis.androidapp.searching.GalleryItem

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}