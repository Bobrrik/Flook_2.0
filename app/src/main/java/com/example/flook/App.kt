package com.example.flook

import android.app.Application
import com.example.flook.di.AppComponent
import com.example.flook.di.DaggerAppComponent
import com.example.flook.di.modules.DatabaseModule
import com.example.flook.di.modules.DomainModule
import com.example.flook.di.modules.RemoteModule


class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this

        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}