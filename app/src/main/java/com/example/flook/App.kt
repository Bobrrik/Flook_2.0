package com.example.flook

import android.app.Application
import com.example.flook.di.AppComponent
import com.example.flook.di.DaggerAppComponent
import com.example.flook.domain.Interactor


class App : Application() {

lateinit var dagger:AppComponent
    //lateinit var interactor: Interactor


    override fun onCreate() {
        super.onCreate()



//        startKoin {
//            androidContext(this@App)
//            androidLogger()
//            modules(listOf(DI.mainModule))
//        }

        instance = this

        dagger = DaggerAppComponent.create()
//        repo = BaseFilm()

//        val okHttpClient = OkHttpClient.Builder()
//            .callTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                if (BuildConfig.DEBUG) {
//                    level = HttpLoggingInterceptor.Level.BASIC
//                }
//            })
//            .build()
//
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(ApiConstants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//        retrofitService = retrofit.create(TmdbApi::class.java)
//
//        interactor = Interactor(repo, retrofitService)

    }

    companion object {
        lateinit var instance: App
            private set
    }

}