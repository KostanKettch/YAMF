package com.kostankettch.yamf.domain


import com.kostankettch.yamf.API
import com.kostankettch.yamf.data.*
import com.kostankettch.yamf.data.entity.Cinema
import com.kostankettch.yamf.data.entity.TmdbResults
import com.kostankettch.yamf.utils.Converter
import com.kostankettch.yamf.viewmodel.HomeFragmentViewModel
import com.kostankettch.yamf.data.preferences.PreferenceProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(
    private val repo: MainRepository,
    private val retrofitService: TmdbApi,
    private val preferences: PreferenceProvider
) {
    var progressBar: BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun getMoviesFromApi(page: Int) {
        progressBar.onNext(true)

        retrofitService.getMovies(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page)
            .enqueue(object : Callback<TmdbResults> {
                override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                    val list = Converter.convertApiListToDtoList(response.body()?.tmdbMovies)
                    Completable.fromSingle<List<Cinema>> {
                        repo.putToDb(list)
                    }
                        .subscribeOn(Schedulers.io())
                        .subscribe()
                    progressBar.onNext(false)
                }

                override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                    progressBar.onNext(false)
                }
            })
    }

    fun getSearchResultFromApi(search: String): Observable<List<Cinema>> =
        retrofitService.getMovieFromSearch(API.KEY, "ru-RU", search, 1)
            .map {
                Converter.convertApiListToDtoList(it.tmdbMovies)
            }

    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getMoviesFromDb(): Observable<List<Cinema>> = repo.getAllFromDb()
}