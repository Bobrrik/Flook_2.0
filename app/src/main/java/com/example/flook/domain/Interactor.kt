package com.example.flook.domain

import com.example.flook.data.BaseFilm

class Interactor(val repo: BaseFilm) {
    fun getFilmsDB(): List<Films> = repo.BaseFilms()
}