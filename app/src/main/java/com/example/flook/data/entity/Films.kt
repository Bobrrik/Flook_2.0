package com.example.flook.data.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "cached_films", indices = [Index(value = ["title"], unique = true)])
data class Films(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") var textLong: String,
    @ColumnInfo(name = "poster_path") val poster: String,
    @ColumnInfo(name = "vote_average") val rating: Int = 50,
    @ColumnInfo(name = "beastLi") var beast: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
    : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(title)
        p0.writeString(textLong)
        p0.writeString(poster)
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

