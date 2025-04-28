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

   // private val interactor: Interactor by inject()
    var page: Int = 1

    init {
        App.instance.dagger.inject(this)
                                    //          Я к сожелению так и не нашёл как и что я должен тут
        newPage(page)               //          изменить что бы реализовать хотя бы замену страницы, не то что её пролонгацию

    }

    interface ApiCallback {
        fun onSuccess(films: List<Films>)
        fun onFailure()
    }

    fun newPage(_page: Int = 1) {
        val films = interactor.getFilmsFromApi(_page, object : ApiCallback {
            override fun onFailure() {
                Log.e("PrIKOL", "что то не по плану")
            }

            override fun onSuccess(films: List<Films>) {
                filmsListLiveData.postValue(films)
            }
        })
    }
}

