package com.example.flook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flook.App
import com.example.flook.domain.Interactor
import javax.inject.Inject

class SettingsFragmentViewModel : ViewModel() {

    @Inject
    lateinit var interactor: Interactor
    val categoryPropertyLifeData: MutableLiveData<String> = MutableLiveData()

    init {
        App.instance.dagger.inject(this)
        getCategoryProperty()
    }

    private fun getCategoryProperty() {
        categoryPropertyLifeData.value = interactor.getDefaultCategoryFromPreferences()
    }

    fun putCategoryProperty(category: String) {
        interactor.saveDefaultCategoryFromPreferences(category = category)
        getCategoryProperty()
    }
}