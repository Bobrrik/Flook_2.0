package com.example.flook.di

import com.example.flook.di.modules.DatabaseModule
import com.example.flook.di.modules.DomainModule
import com.example.flook.di.modules.RemoteModule
import com.example.flook.viewmodel.HomeFragmentViewModel
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
}


    //    single<TmdbApi> {
//                .callTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .addInterceptor(HttpLoggingInterceptor().apply {
//                    if (BuildConfig.DEBUG) {
//                        level = HttpLoggingInterceptor.Level.BASIC
//                    }
//                })
//                .build()
//
//            val retrofit = Retrofit.Builder()
//                .baseUrl(ApiConstants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build()
//
//            retrofit.create(TmdbApi::class.java)
//}
//
//      single { Interactor(get(), get()) }
//  }
//}