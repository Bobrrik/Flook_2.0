package com.example.flook.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.flook.data.entity.Films
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("Select * From cached_films")
    fun getCachedFilms(): Flow<List<Films>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Films>)

    @Update
    fun beastUp(film: Films) {
        film.beast = true
    }
}