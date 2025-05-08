package com.example.flook.di.modules


import android.content.Context
import com.example.flook.data.RepositoryBD
import com.example.flook.data.Setting
import com.example.flook.data.TmdbApi
import com.example.flook.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule(val context: Context) {

    @Provides
    fun provContext() = context

    @Provides
    fun provPreferences(context: Context) = Setting(context)

    @Provides
    @Singleton
    fun provInteractor(base: RepositoryBD, tmdbApi: TmdbApi, preferences: Setting) = Interactor(
        repo = base,
        retrofitService = tmdbApi,
        preferences = preferences
    )

}