package com.kostankettch.yamf.data

import com.kostankettch.yamf.data.dao.CinemaDao
import com.kostankettch.yamf.data.entity.Cinema
import kotlinx.coroutines.flow.Flow


class MainRepository(private val cinemaDao: CinemaDao) {
    fun putToDb(movies: List<Cinema>) {
        cinemaDao.insertAll(movies)
    }

    fun getAllFromDb(): Flow<List<Cinema>> {
        return cinemaDao.getCachedMovies()
    }
}