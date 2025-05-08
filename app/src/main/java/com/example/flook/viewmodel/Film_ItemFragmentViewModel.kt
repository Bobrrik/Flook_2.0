package com.example.flook.viewmodel

import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.flook.App
import com.example.flook.data.db.DataBaseHelper
import com.example.flook.domain.Interactor
import javax.inject.Inject

class Film_ItemFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    val cv = ContentValues()

    init {
        App.instance.dagger.inject(this)
    }

    fun swapBeast(context: Context, value: String, beastLi: Int) {
        val dataBaseHelper = DataBaseHelper(context)
        val sqlDb = dataBaseHelper.readableDatabase

        cv.put(DataBaseHelper.COLUMN_BEAST, 1)           // реализовать удаление из бестов

        sqlDb.update(DataBaseHelper.TABLE_NAME, cv, DataBaseHelper.COLUMN_TITLE + "=?", arrayOf(value))
    }
}