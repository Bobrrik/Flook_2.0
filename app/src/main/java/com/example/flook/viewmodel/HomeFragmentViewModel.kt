package com.example.flook.viewmodel


import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.flook.App
import com.example.flook.data.entity.Films
import com.example.flook.domain.Interactor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class HomeFragmentViewModel() : ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    val filmsListLiveData: Flow<List<Films>>
    var progressBarShow = Channel<Boolean>()

    init {
        App.instance.dagger.inject(this)
        progressBarShow = interactor.progressBarState
        filmsListLiveData = interactor.getFilmsFromDB()
        newPage()
    }

    fun newPage(_page: Int = 1) {
       interactor.getFilmsFromApi(_page)
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }
}

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(
                "SingleLiveEvent",
                "Multiple observers registered but only one will be notified of changes."
            )
        }
        // Наблюдение за внутренним MutableLiveData
        super.observe(owner) { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }
}

