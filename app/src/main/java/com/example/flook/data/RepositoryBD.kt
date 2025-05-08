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
            put(DataBaseHelper.COLUMN_RATING, film.rating)  // !!!!!  плавающая точка
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
                val rating = (cursor.getDouble(4) * 10).toInt()

                result.add(Films(title, textLong, poster, rating))
            } while (cursor.moveToNext())
        }
        return result
    }


}