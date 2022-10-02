package com.kostankettch.yamf.domain


import com.kostankettch.yamf.API
import com.kostankettch.yamf.data.*
import com.kostankettch.yamf.data.entity.Cinema
import com.kostankettch.yamf.data.entity.TmdbResults
import com.kostankettch.yamf.utils.Converter
import com.kostankettch.yamf.viewmodel.HomeFragmentViewModel
import com.kostankettch.yamf.data.preferences.PreferenceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var progressBar = Channel<Boolean> (Channel.CONFLATED)

    fun getMoviesFromApi(page: Int) {
        scope.launch {
            progressBar.send(true)
        }
        retrofitService.getMovies(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {
            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbMovies)
                scope.launch {
                repo.putToDb(list)
                progressBar.send(false)
                }
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                scope.launch {
                progressBar.send(false)
                }
            }
        })
    }

    fun saveDefaultCategoryToPreferences(category: String){
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getMoviesFromDb(): Flow<List<Cinema>> = repo.getAllFromDb()
}