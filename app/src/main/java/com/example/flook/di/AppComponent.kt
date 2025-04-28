package com.example.flook.di

import com.example.flook.di.modules.DatabaseModule
import com.example.flook.di.modules.DomainModule
import com.example.flook.di.modules.RemoteModule
import com.example.flook.viewmodel.HomeFragmentViewModel
import com.example.flook.viewmodel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton


//object DI {

    // val mainModule = module {
    //      single { BaseFilm() }

//            val okHttpClient = OkHttpClient.Builder()
@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class,
    ]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}


