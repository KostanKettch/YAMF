package com.kostankettch.yamf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kostankettch.yamf.App
import com.kostankettch.yamf.domain.Cinema
import com.kostankettch.yamf.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val moviesListLiveData: MutableLiveData<List<Cinema>> = MutableLiveData()

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        getMovies()
    }

    fun getMovies() {
        interactor.getMoviesFromApi(1, object : ApiCallback {
            override fun onSuccess(movies: List<Cinema>) {
                moviesListLiveData.postValue(movies)
            }

            override fun onFailure() {
                moviesListLiveData.postValue(interactor.getMoviesFromDB())
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(movies: List<Cinema>)
        fun onFailure()
    }
}