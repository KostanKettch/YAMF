package com.kostankettch.yamf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kostankettch.yamf.App
import com.kostankettch.yamf.domain.Cinema
import com.kostankettch.yamf.domain.Interactor

class HomeFragmentViewModel : ViewModel() {
    val moviesListLiveData = MutableLiveData<List<Cinema>>()
    private  var  interactor: Interactor = App.instance.interactor
    init {
        val cinema = interactor.getMoviesDB()
        moviesListLiveData.postValue(cinema)
    }
}