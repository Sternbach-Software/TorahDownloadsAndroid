package tech.torah.aldis.androidapp.codeForParsingJSONcopiedFromAndroidBookNOT_YET_REFACTORED.api

import com.google.gson.annotations.SerializedName
import tech.torah.aldis.androidapp.codeForParsingJSONcopiedFromAndroidBookNOT_YET_REFACTORED.GalleryItem

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}