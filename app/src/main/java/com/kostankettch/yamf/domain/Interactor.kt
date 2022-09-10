package com.kostankettch.yamf.domain


import com.kostankettch.yamf.API
import com.kostankettch.yamf.data.*
import com.kostankettch.yamf.data.entity.TmdbResults
import com.kostankettch.yamf.utils.Converter
import com.kostankettch.yamf.viewmodel.HomeFragmentViewModel
import com.kostankettch.yamf.data.preferences.PreferenceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    fun getMoviesFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getMovies(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {
            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbMovies)
                list.forEach {
                    repo.putToDb(cinema = it) }
                callback.onSuccess(list)
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    fun saveDefaultCategoryToPreferences(category: String){
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getMoviesFromDB(): List<Cinema> = repo.getAllFromDB()
}