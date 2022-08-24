package com.kostankettch.yamf.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.kostankettch.yamf.data.Entity.TmdbResults

interface TmdbApi {
    @GET("3/movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TmdbResults>

}