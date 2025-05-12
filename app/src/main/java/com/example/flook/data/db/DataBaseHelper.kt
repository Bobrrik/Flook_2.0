package com.example.flook.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_TITLE TEXT UNIQUE," +          //  1
                    "$COLUMN_POSTER TEXT," +                //  2
                    "$COLUMN_DESCRIPTION TEXT," +           //  3
                    "$COLUMN_RATING REAL," +                //  4
                    "$COLUMN_BEAST INTEGER DEFAULT '0' )"   //  5
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {}

    companion object {
        private const val DB_NAME = "films.db"
        private const val DB_VERSION = 1

        const val TABLE_NAME = "films_table"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_POSTER = "poster_path"
        const val COLUMN_DESCRIPTION = "overview"
        const val COLUMN_RATING = "vote_average"
        const val COLUMN_BEAST = "beast"
    }
}