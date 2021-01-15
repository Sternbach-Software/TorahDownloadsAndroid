package tech.torah.aldis.androidapp.dataClassesAndInterfaces

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Category(
    val id: Int = 6,
    val name: String = "Chumash",
    val url: String = "c-6-torah.html",
    val label: String = "<a href=\"c-6-torah.html\">Chumash</a>",
    val children: ArrayList<Category>? = arrayListOf()
) : TorahFilter, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createTypedArrayList(CREATOR)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(label)
        parcel.writeTypedList(children)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
    val hasChildren = children?.size ?: -1 > 0
}