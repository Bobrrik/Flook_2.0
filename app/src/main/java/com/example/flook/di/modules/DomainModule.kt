package com.example.flook.di.modules


import com.example.flook.data.BaseFilm
import com.example.flook.data.TmdbApi
import com.example.flook.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provInteractor(base: BaseFilm, tmdbApi: TmdbApi) = Interactor(base, tmdbApi)

}