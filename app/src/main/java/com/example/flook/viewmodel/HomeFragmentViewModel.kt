package com.example.flook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flook.App
import com.example.flook.domain.Films

class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData = MutableLiveData<List<Films>>()
    private var interactor = App.instance.interactor

    init {
        val films = interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onFailure() {

            }

            override fun onSuccess(films: List<Films>) {
                filmsListLiveData.postValue(films)
            }
        })

    }
    interface ApiCallback {
        fun onSuccess(films: List<Films>)
        fun onFailure()
    }
}

