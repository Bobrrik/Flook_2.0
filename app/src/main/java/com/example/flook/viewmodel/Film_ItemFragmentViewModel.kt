package com.example.flook.viewmodel

import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.flook.App
import com.example.flook.data.db.DataBaseHelper
import com.example.flook.data.entity.Films
import com.example.flook.domain.Interactor
import javax.inject.Inject

class Film_ItemFragmentViewModel : ViewModel() {
    val cv = ContentValues()
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
    }

    fun swapBeast(film : Films) {
        interactor.swapBeast(film)
    }

//
//    fun swapBeast(context: Context, value: String, beastLi: Int) {
//        val dataBaseHelper = DataBaseHelper(context)
//        val sqlDb = dataBaseHelper.readableDatabase
//        cv.put(DataBaseHelper.COLUMN_BEAST, beastLi)           // изменение статуса изброности
//
//        sqlDb.update(DataBaseHelper.TABLE_NAME, cv, DataBaseHelper.COLUMN_TITLE + "=?", arrayOf(value))
//    }
}