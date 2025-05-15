package com.example.flook.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.flook.data.entity.Films

@Dao
interface FilmDao {
    @Query("Select * From cached_films")
    fun getCachedFilms(): List<Films>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Films>)

    @Update
    fun beastUp(film: Films) {
        film.beast = true
    }
}