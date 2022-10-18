package com.kostankettch.yamf.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.kostankettch.yamf.data.entity.TmdbResults
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Path

interface TmdbApi {
    @GET("3/movie/{category}")
    fun getMovies(
        @Path("category") category: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TmdbResults>

    @GET("3/search/movie")
    fun getMovieFromSearch(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Observable<TmdbResults>
}