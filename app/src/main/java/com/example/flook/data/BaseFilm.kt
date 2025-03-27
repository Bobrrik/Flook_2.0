package com.example.flook.data

import com.example.flook.R
import com.example.flook.domain.Films

class BaseFilm {
    private val base = mutableListOf(
        Films("Фильм 1", "dddddd", R.drawable.film1, 74),
        Films("fff", "sdfsdf", R.drawable.film2, 34, true),
        Films("Фильм 3", "sdfsdf", R.drawable.film3, 12),
        Films("Фильм 4", "sdfsdf", R.drawable.film4, 88),
        Films("Фильм 5", "sdfsdf", R.drawable.film5, 2),
        Films("Фильм 6", "sdfsdf", R.drawable.film6, 67),
        Films("Фильм 7", "sdfsdf", R.drawable.film7, 23, true),
        Films("Фильм 8", "sdfsdf", R.drawable.film8, 77, true)
    )

    fun BaseFilms(): List<Films> {
        return base
    }

    fun favoriteUp(name: String) {
        val item = Films("Новинка", "sdfsdf", R.drawable.film8)
        item.beast = true


        base.removeAll { it.title == name }
        base.add(item)

//        for (i in 0..base.size - 1) {
//            if (base[i].title == name) {
//                base[i].textLong = "trusdafasdfasasdfasfdasdfasdffasdfasfasfasdfassadfasdfsafasdfasfdasfasfsafasfasfdas"
//            }
//        }
    }

    fun favoriteDown(films: Films) {
        films.beast = false
    }
}