package tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants

import android.os.Parcel
import android.os.Parcelable

data class ShiurCategoryPage(
val ShiurID:String? = "24337",
val Title:String? = "Introduction to Pirkei Avot - Ethics of the Fathers",
val Length:String? = "3829",
val FormattedLength:String? = "63",
val SpeakerID:String? = "14",
val Speaker:String? = "Rabbi Mordechai Becher",
val SpeakerSE:String? = "rabbi-mordechai-becher"
):Shiur(ShiurID,Title,Length,Speaker), Parcelable {
    constructor(parcel: Parcel) : this(
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
        parcel.writeString(ShiurID)
        parcel.writeString(Title)
        parcel.writeString(Length)
        parcel.writeString(FormattedLength)
        parcel.writeString(SpeakerID)
        parcel.writeString(Speaker)
        parcel.writeString(SpeakerSE)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShiurCategoryPage> {
        override fun createFromParcel(parcel: Parcel): ShiurCategoryPage {
            return ShiurCategoryPage(parcel)
        }

        override fun newArray(size: Int): Array<ShiurCategoryPage?> {
            return arrayOfNulls(size)
        }
    }
}
