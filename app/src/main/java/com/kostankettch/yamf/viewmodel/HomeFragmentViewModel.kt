package com.kostankettch.yamf.viewmodel

import androidx.lifecycle.ViewModel
import com.kostankettch.yamf.App
import com.kostankettch.yamf.data.entity.Cinema
import com.kostankettch.yamf.domain.Interactor
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {


    @Inject
    lateinit var interactor: Interactor
    val moviesListData: Observable<List<Cinema>>
    val showProgressBar: BehaviorSubject<Boolean>

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