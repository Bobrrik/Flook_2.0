package com.example.flook.di.modules

import com.example.flook.data.BaseFilm
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRepository() = BaseFilm()
}