package com.example.flook.data

import android.content.ContentValues
import android.database.Cursor
import com.example.flook.data.db.DataBaseHelper
import com.example.flook.domain.Films

class RepositoryBD(dataBaseHelper: DataBaseHelper) {
    private val sqlDB = dataBaseHelper.readableDatabase
    private lateinit var cursor: Cursor

//    val base = mutableListOf(
//        Films("Фильм 1", "dddddd", "R.drawable.film1", 74, true),
//        Films("Фильм 4", "sdfsdf", R.drawable.film4, 88),
//        Films("Фильм 8", "sdfsdf", R.drawable.film8, 77, true)
//    )

    fun putToBD(film: Films) {
        val cv = ContentValues()
        cv.apply {
            put(DataBaseHelper.COLUMN_TITLE, film.title)
            put(DataBaseHelper.COLUMN_POSTER, film.poster)
            put(DataBaseHelper.COLUMN_DESCRIPTION, film.textLong)
            put(DataBaseHelper.COLUMN_RATING, (film.rating).toDouble() / 10)
            put(DataBaseHelper.COLUMN_BEAST, toIntFromBoolean(film.beast))
        }
        sqlDB.insert(DataBaseHelper.TABLE_NAME, null, cv)
    }

    fun getAllFromBD(): List<Films> {
        cursor = sqlDB.rawQuery("SELECT * FROM ${DataBaseHelper.TABLE_NAME}", null)
        val result = mutableListOf<Films>()

        if (cursor.moveToFirst()) {
            do {
                val title = cursor.getString(1)
                val poster = cursor.getString(2)
                val textLong = cursor.getString(3)
                val rating = (cursor.getDouble(4) * 10).toInt()  // приходит Double а нужен Int
                val best = toBooleanFromInt(cursor.getInt(5))       // приходит Int нужен Boolean

                result.add(Films(title, textLong, poster, rating, best))
            } while (cursor.moveToNext())
        }
        return result
    }

    fun toBooleanFromInt(valueBD: Int): Boolean =
        if (valueBD == 1) true else false     // возможны изменения

    fun toIntFromBoolean(boolean: Boolean): Int = if (boolean) 1 else 0
}