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
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class HomeFragmentViewModel() : ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    //    lateinit var _context : SingleLiveEvent<Application>
    val filmsListLiveData: LiveData<List<Films>>
    var progressBarShow = MutableLiveData<Boolean>()

    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmsFromDB()
        newPage()
//        _context.postValue(context)
    }

    fun newPage(_page: Int = 1) {
        progressBarShow.postValue(true)
        interactor.getFilmsFromApi(_page, object : ApiCallback {
            override fun onSuccess() {
                progressBarShow.postValue(false)
                Log.e("!!! PrIKOL", "всё по плану")
            }

            override fun onFailure() {
                Log.e("!!! PrIKOL", "что то не по плану")
//                Toast.makeText(_context.value,"задание со звёздоччкой",Toast.LENGTH_SHORT).show()
                progressBarShow.postValue(false)
                Log.e("!!! PrIKOL", "Скоректировали")
            }
        })
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

