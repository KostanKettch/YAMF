package com.kostankettch.yamf.viewmodel

import androidx.lifecycle.ViewModel
import com.kostankettch.yamf.App
import com.kostankettch.yamf.data.entity.Cinema
import com.kostankettch.yamf.domain.Interactor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {


    @Inject
    lateinit var interactor: Interactor
    val moviesListData: Flow<List<Cinema>>
    val showProgressBar: Channel<Boolean>

    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBar
        moviesListData = interactor.getMoviesFromDb()
        getMovies()
    }

    fun getMovies() {

        interactor.getMoviesFromApi(1)
    }
}