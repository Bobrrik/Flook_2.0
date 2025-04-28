package com.example.flook.domain

import com.example.flook.API
import com.example.flook.data.BaseFilm
import com.example.flook.data.Setting
import com.example.flook.data.TmdbApi
import com.example.flook.utils.Converter
import com.example.flook.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(
    val repo: BaseFilm,
    private val retrofitService: TmdbApi,
    private val preferences: Setting
) {
    fun getFilmsFromApi(
        page: Int,
        callback: HomeFragmentViewModel.ApiCallback
    )      //    : List<Films> = repo.base
    {
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
                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.tmdbFilms))
            }
        })
    }

    fun saveDefaultCategoryFromPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
}