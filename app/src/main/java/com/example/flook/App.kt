package com.example.flook

import android.app.Application
import com.example.flook.di.DI
import com.example.flook.domain.Interactor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    //    lateinit var repo: BaseFilm
    lateinit var interactor: Interactor
//    lateinit var retrofitService: TmdbApi

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(DI.mainModule))
        }


//        instance = this
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