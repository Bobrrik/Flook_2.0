package com.example.flook.data

import androidx.lifecycle.LiveData
import com.example.flook.data.dao.FilmDao
import com.example.flook.data.entity.Films
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors


class RepositoryBD(private val filmDao: FilmDao) {

    fun putToBD(films: List<Films>) {
        Executors.newSingleThreadExecutor().execute { filmDao.insertAll(films) }
       // filmDao.insertAll(films)
     }

    fun getAllFromBD(): Flow<List<Films>> {
        return filmDao.getCachedFilms()
    }

    fun swapBeast(film : Films) {
        Executors.newSingleThreadExecutor().execute { filmDao.beastUp(film) }
    }
}