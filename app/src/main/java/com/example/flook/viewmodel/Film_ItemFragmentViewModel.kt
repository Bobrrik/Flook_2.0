package com.example.flook.viewmodel

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import com.example.flook.App
import com.example.flook.data.entity.Films
import com.example.flook.domain.Interactor
import java.net.URL
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Film_ItemFragmentViewModel : ViewModel() {
    val cv = ContentValues()

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
    }

    fun swapBeast(film: Films) {
        interactor.swapBeast(film)
    }

    suspend fun LodePoster(urlString: String): Bitmap {
        return suspendCoroutine {
            val url = URL(urlString)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            it.resume(bitmap)
        }
    }
}