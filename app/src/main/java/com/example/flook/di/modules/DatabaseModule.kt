package com.example.flook.di.modules

import android.content.Context
import com.example.flook.data.RepositoryBD
import com.example.flook.data.db.DataBaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provDataBaseHelper(context: Context) = DataBaseHelper(context)

    @Provides
    @Singleton
    fun provideRepository(dataBaseHelper: DataBaseHelper) = RepositoryBD(dataBaseHelper)
}