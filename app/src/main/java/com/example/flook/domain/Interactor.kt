package com.example.flook.domain

import com.example.flook.API
import com.example.flook.data.RepositoryBD
import com.example.flook.data.Setting
import com.example.flook.data.TmdbApi
import com.example.flook.data.entity.Films
import com.example.flook.utils.Converter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(
    val repo: RepositoryBD,
    private val retrofitService: TmdbApi,
    private val preferences: Setting
) {
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var progressBarState = Channel<Boolean>(Channel.CONFLATED)

    fun getFilmsFromApi(page: Int) {
        scope.launch {
            progressBarState.send(true)
        }

        retrofitService.getFilms(
            category = getDefaultCategoryFromPreferences(),
            apiKey = API.KAY,
            language = "ru-RU",
            page = page
        ).enqueue(object : Callback<TmdbResultsDto> {

            override fun onResponse(
                call: Call<TmdbResultsDto>,
                response: Response<TmdbResultsDto>
            ) {
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                scope.launch {
                    repo.putToBD(films = list)
                    progressBarState.send(false)
                }
            }

            override fun onFailure(p0: Call<TmdbResultsDto>, p1: Throwable) {
                scope.launch {
                    progressBarState.send(false)
                }
            }
        })
    }

    fun getFilmsFromDB(): Flow<List<Films>> = repo.getAllFromBD()

    fun saveDefaultCategoryFromPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun swapBeast(film: Films) {
        repo.swapBeast(film)
    }
}