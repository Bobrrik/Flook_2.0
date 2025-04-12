package com.example.flook.utils

import com.example.flook.data.TmdbFilm
import com.example.flook.domain.Films

object Converter {
    fun convertApiListToDtoList(list: List<TmdbFilm>?): List<Films> {
        val result = mutableListOf<Films>()
        list?.forEach {
            result.add(
                Films(
                    title = it.title,
                    textLong = it.overview,
                    poster = it.posterPath,
                    rating = (it.voteAverage * 10).toInt(),
                    beast = false
                )
            )
        }
        return result
    }
}