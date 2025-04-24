package com.example.flook.utils

import com.example.flook.data.TmdbFilm
import com.example.flook.domain.Films
import kotlin.random.Random

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
                    beast = random()
                )
            )
        }
        return result
    }

    fun random(): Boolean {
        val random = Random.nextInt(0, 10)
        return if (random > 7) true
        else false
    }
}