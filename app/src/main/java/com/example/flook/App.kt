package com.example.flook

import android.app.Application
import com.example.flook.data.BaseFilm
import com.example.flook.domain.Interactor

class App : Application() {

    lateinit var repo: BaseFilm
    lateinit var interactor: Interactor

    override fun onCreate() {
        super.onCreate()
        instance = this
        repo = BaseFilm()
        interactor = Interactor(repo)

    }

    companion object {
        lateinit var instance: App
            private set
    }

}