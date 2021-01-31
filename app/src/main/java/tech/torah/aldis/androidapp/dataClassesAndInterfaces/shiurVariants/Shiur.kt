package tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants

import android.os.Parcel
import android.os.Parcelable
import tech.torah.aldis.androidapp.dataClassesAndInterfaces.OneOfMyClasses

open class Shiur(val baseId: String? = "test", val baseTitle: String? ="test", val baseLength: String?="test", val baseSpeaker: String?="test") :
    Parcelable, OneOfMyClasses {
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

    override fun toString(): String {
//        return "baseSpeaker=$baseSpeaker,baseLength=$baseLength,baseTitle=$baseTitle" //for testing
      return "baseId=$baseId,baseTitle=$baseTitle,baseLength=$baseLength,baseSpeaker=$baseSpeaker"
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