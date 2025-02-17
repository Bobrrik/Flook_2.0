package com.example.flook

import android.os.Parcel
import android.os.Parcelable


data class Films(val title: String, val textLong: String, val poster: Int, var beast:Boolean = false) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(title)
        p0.writeString(textLong)
        p0.writeInt(poster)
    }

    companion object CREATOR : Parcelable.Creator<Films> {
        override fun createFromParcel(parcel: Parcel): Films {
            return Films(parcel)
        }

        override fun newArray(size: Int): Array<Films?> {
            return arrayOfNulls(size)
        }
    }
}