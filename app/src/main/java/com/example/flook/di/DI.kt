package com.example.flook.di

import com.airbnb.lottie.BuildConfig
import com.example.flook.data.ApiConstants
import com.example.flook.data.BaseFilm
import com.example.flook.data.TmdbApi
import com.example.flook.domain.Interactor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object DI {
    val mainModule = module {
        single { BaseFilm() }

        single<TmdbApi> {
            val okHttpClient = OkHttpClient.Builder()
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }
                })
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            retrofit.create(TmdbApi::class.java)
        }

        single { Interactor(get(), get()) }
    }
}

fun remote(){

}