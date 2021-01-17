package tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants

import android.os.Parcel
import android.os.Parcelable

open class Shiur(val baseId: String? = "test", val baseTitle: String? ="test", val baseLength: String?="test", val baseSpeaker: String?="test") :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(baseId)
        parcel.writeString(baseTitle)
        parcel.writeString(baseLength)
        parcel.writeString(baseSpeaker)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Shiur> {
        override fun createFromParcel(parcel: Parcel): Shiur {
            return Shiur(parcel)
        }

        override fun newArray(size: Int): Array<Shiur?> {
            return arrayOfNulls(size)
        }
    }
}
//Not called id, title, etc. because of JVM override issues.