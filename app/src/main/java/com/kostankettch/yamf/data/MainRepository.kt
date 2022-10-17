package com.kostankettch.yamf.data

import com.kostankettch.yamf.data.dao.CinemaDao
import com.kostankettch.yamf.data.entity.Cinema
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow



class MainRepository(private val cinemaDao: CinemaDao) {
    fun putToDb(movies: List<Cinema>) {
        cinemaDao.insertAll(movies)
    }

    fun getAllFromDb(): Observable<List<Cinema>> {
        return cinemaDao.getCachedMovies()
    }
}