package com.example.flook.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flook.App
import com.example.flook.domain.Films
import com.example.flook.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel() : ViewModel() {
    val filmsListLiveData = MutableLiveData<List<Films>>()

    @Inject
    lateinit var interactor: Interactor

    var page: Int = 1

    init {
        App.instance.dagger.inject(this)
        newPage()
        //          Я к сожелению так и не нашёл как и что я должен тут
        //          изменить что бы реализовать хотя бы замену страницы, не то что её пролонгацию
    }

    fun newPage(_page: Int = 1) {
        interactor.getFilmsFromApi(_page, object : ApiCallback {
            override fun onFailure() {
                Log.e("PrIKOL", "что то не по плану")
            }

            override fun onSuccess(films: List<Films>) {
                filmsListLiveData.postValue(films)
                Log.e("PrIKOL", "всё по плану")
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(films: List<Films>)
        fun onFailure()
    }
}

