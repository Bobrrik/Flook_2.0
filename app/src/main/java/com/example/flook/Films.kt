package com.example.flook

import android.os.Parcel
import android.os.Parcelable


data class Films(
    val title: String,
    val textLong: String,
    val poster: Int,
    var beast: Boolean = false
) : Parcelable {
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

class Base {
    val base = listOf(
        Films("Фильм 1", "dddddd", R.drawable.film1),
        Films("fff", "sdfsdf", R.drawable.film2),
        Films("Фильм 3", "sdfsdf", R.drawable.film3),
        Films("Фильм 4", "sdfsdf", R.drawable.film4),
        Films("Фильм 5", "sdfsdf", R.drawable.film5),
        Films("Фильм 6", "sdfsdf", R.drawable.film6),
        Films("Фильм 7", "sdfsdf", R.drawable.film7),
        Films("Фильм 8", "sdfsdf", R.drawable.film8)
    )

    fun BaseFilms(): List<Films> {
        return base
    }

    fun favoriteUp(films: Films) {

    }

    fun favoriteDown(films: Films) {}
}