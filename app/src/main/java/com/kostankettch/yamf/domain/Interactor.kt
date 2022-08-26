package com.kostankettch.yamf.domain

import com.kostankettch.yamf.API
import com.kostankettch.yamf.data.*
import com.kostankettch.yamf.data.Entity.TmdbResults
import com.kostankettch.yamf.utils.Converter
import com.kostankettch.yamf.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(val repo: MainRepository, private val retrofitService: TmdbApi) {
    fun getMoviesFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getMovies(API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {
            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.tmdbMovies))
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}