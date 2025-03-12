package com.example.flook

import android.os.Parcel
import android.os.Parcelable


data class Films(
    val title: String,
    var textLong: String,
    val poster: Int,
    val rating: Int = 50,
    var beast: Boolean = false
) : Parcelable {

    // val title: String, var textLong: String, val poster: Int, var beast: Boolean = false

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
    private val base = mutableListOf(
        Films("Фильм 1", "dddddd", R.drawable.film1, 74),
        Films("fff", "sdfsdf", R.drawable.film2, 34, true),
        Films("Фильм 3", "sdfsdf", R.drawable.film3, 12),
        Films("Фильм 4", "sdfsdf", R.drawable.film4, 88),
        Films("Фильм 5", "sdfsdf", R.drawable.film5, 2),
        Films("Фильм 6", "sdfsdf", R.drawable.film6, 67),
        Films("Фильм 7", "sdfsdf", R.drawable.film7, 23, true),
        Films("Фильм 8", "sdfsdf", R.drawable.film8, 77, true)
    )

    fun BaseFilms(): List<Films> {
        return base
    }

    fun favoriteUp(name: String) {
        val item = Films("Новинка", "sdfsdf", R.drawable.film8)
        item.beast = true


        base.removeAll { it.title == name }
        base.add(item)

//        for (i in 0..base.size - 1) {
//            if (base[i].title == name) {
//                base[i].textLong = "trusdafasdfasasdfasfdasdfasdffasdfasfasfasdfassadfasdfsafasdfasfdasfasfsafasfasfdas"
//            }
//        }
    }

    fun favoriteDown(films: Films) {
        films.beast = false
    }
}