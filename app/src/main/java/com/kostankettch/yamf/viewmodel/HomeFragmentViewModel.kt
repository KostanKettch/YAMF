package com.kostankettch.yamf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kostankettch.yamf.domain.Cinema
import com.kostankettch.yamf.domain.Interactor
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeFragmentViewModel : ViewModel() , KoinComponent{
    val moviesListLiveData: MutableLiveData<List<Cinema>> = MutableLiveData()
    private val interactor: Interactor by inject()




    init {
        interactor.getMoviesFromApi(1, object : ApiCallback {
            override fun onSuccess(movies: List<Cinema>) {
                moviesListLiveData.postValue(movies)
            }

            override fun onFailure() {
            }
        })
    }
    interface ApiCallback{
        fun onSuccess(movies: List<Cinema>)
        fun onFailure()
    }
}