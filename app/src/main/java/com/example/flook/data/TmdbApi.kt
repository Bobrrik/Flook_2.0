package com.example.flook.data

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.flook.domain.TmdbResultsDto
import retrofit2.Call

interface TmdbApi {
    @GET("3/movie/popular")
    fun getFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TmdbResultsDto>
}