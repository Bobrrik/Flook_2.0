package com.example.flook.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flook.data.dao.FilmDao
import com.example.flook.data.entity.Films

@Database(entities = [Films::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}