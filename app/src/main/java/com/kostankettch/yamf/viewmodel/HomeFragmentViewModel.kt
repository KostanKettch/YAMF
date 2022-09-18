package com.kostankettch.yamf.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kostankettch.yamf.App
import com.kostankettch.yamf.data.entity.Cinema
import com.kostankettch.yamf.domain.Interactor
import java.util.concurrent.Executors
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()

    @Inject
    lateinit var interactor: Interactor
    val moviesListLiveData: LiveData<List<Cinema>>

    init {
        App.instance.dagger.inject(this)
        moviesListLiveData = interactor.getMoviesFromDb()
        getMovies()
    }

    fun getMovies() {
        showProgressBar.postValue(true)
        interactor.getMoviesFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)

            }

            override fun onFailure() {
                showProgressBar.postValue(false)

            }
        })
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }
}