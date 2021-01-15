package tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants

import android.os.Parcel
import android.os.Parcelable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.TorahFilter

data class ShiurFullPage(
    val id: String? = "1008064",
    val page_title: String? = "Chinuch - Shiur 1 - Rabbi Yisroel Belsky - TD1008064",
    val title: String? = "Chinuch - Shiur 1",
    val speaker/*Rabbi Yisroel Belsky*/: String? = "Rabbi Yisroel Belsky",
    val speaker_image: String? = "assets\\/speakers\\/64.jpg",
    val length: String? = "83",
    val links: List<String>? = listOf(
        "shiur-1008064-download.mp3",
        "\\/c-223-chinuch-parenting.html",
        "\\/s-64-rabbi-yisroel-belsky.html"
/*
        val download: String = "shiur-1008064-download.mp3",
        val category: String = "\\/c-223-chinuch-parenting.html",
        val speaker*//*\/s-64-rabbi-yisroel-belsky.html" - just represented as "speaker" in actual JSON*//*
: String = "\\/s-64-rabbi-yisroel-belsky.html",
*/
),
    val category: String? = "\\/c-223-chinuch-parenting.html",//is redundant with the links list, but being kept here until better solution
    val attachment: String? = "",
    val description: String? = "",
    val source: String? = "",
    val attachment_name: String? = "",
    val uploaded: String? = "February 5, 2020",
    val language: String? = "",
    val series: String? = "C",
    val quickseries: String? = "",
    val quickseries_name: String? = ""
) : Shiur(id,title,length,speaker), TorahFilter, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(page_title)
        parcel.writeString(title)
        parcel.writeString(speaker)
        parcel.writeString(speaker_image)
        parcel.writeString(length)
        parcel.writeStringList(links)
        parcel.writeString(category)
        parcel.writeString(attachment)
        parcel.writeString(description)
        parcel.writeString(source)
        parcel.writeString(attachment_name)
        parcel.writeString(uploaded)
        parcel.writeString(language)
        parcel.writeString(series)
        parcel.writeString(quickseries)
        parcel.writeString(quickseries_name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShiurFullPage> {
        override fun createFromParcel(parcel: Parcel): ShiurFullPage {
            return ShiurFullPage(parcel)
        }

        override fun newArray(size: Int): Array<ShiurFullPage?> {
            return arrayOfNulls(size)
        }
    }
}


/*
internal class MyDeserializer : JsonDeserializer<javax.swing.text.AbstractDocument.Content?> {
    @Throws(JsonParseException::class)
    fun deserialize(
        je: JsonElement,
        type: Type?,
        jdc: JsonDeserializationContext?
    ): javax.swing.text.AbstractDocument.Content {
        // Get the "content" element from the parsed JSON
        val content: JsonElement = je.getAsJsonObject().get("content")

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return Gson().fromJson(content, javax.swing.text.AbstractDocument.Content::class.java)
    }
}
*/