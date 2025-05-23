package com.example.flook.data

import androidx.lifecycle.LiveData
import com.example.flook.data.dao.FilmDao
import com.example.flook.data.entity.Films
import java.util.concurrent.Executors


class RepositoryBD(private val filmDao: FilmDao) {

    fun putToBD(films: List<Films>) {
        Executors.newSingleThreadExecutor().execute { filmDao.insertAll(films) }
    }

    fun getAllFromBD(): LiveData<List<Films>> {
        return filmDao.getCachedFilms()
    }

    fun swapBeast(film : Films) {
        Executors.newSingleThreadExecutor().execute { filmDao.beastUp(film) }
    }
}