package com.example.flook.di.modules

import android.content.Context
import androidx.room.Room
import com.example.flook.data.RepositoryBD
import com.example.flook.data.dao.FilmDao
import com.example.flook.data.db.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provFilmDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "film_db"
        ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = RepositoryBD(filmDao)
}