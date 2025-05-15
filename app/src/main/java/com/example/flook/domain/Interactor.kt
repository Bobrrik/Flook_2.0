package com.example.flook.domain

import com.example.flook.API
import com.example.flook.data.RepositoryBD
import com.example.flook.data.Setting
import com.example.flook.data.TmdbApi
import com.example.flook.data.entity.Films
import com.example.flook.utils.Converter
import com.example.flook.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(
    val repo: RepositoryBD,
    private val retrofitService: TmdbApi,
    private val preferences: Setting
) {
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(
            category = getDefaultCategoryFromPreferences(),
            apiKey = API.KAY,
            language = "ru-RU",
            page = page
        ).enqueue(object : Callback<TmdbResultsDto> {

            override fun onFailure(p0: Call<TmdbResultsDto>, p1: Throwable) {
                callback.onFailure()
            }

            override fun onResponse(
                call: Call<TmdbResultsDto>,
                response: Response<TmdbResultsDto>
            ) {
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                list.forEach {
                    repo.putToBD(films = list)
                }
                callback.onSuccess(list)
            }
        })
    }

    fun getFilmsFromDB(): List<Films> = repo.getAllFromBD()

    fun saveDefaultCategoryFromPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun swapBeast(film : Films){
        repo.swapBeast(film)
    }
}