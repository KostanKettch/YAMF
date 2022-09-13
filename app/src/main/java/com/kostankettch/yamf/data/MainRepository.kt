package com.kostankettch.yamf.data

import com.kostankettch.yamf.data.dao.CinemaDao
import com.kostankettch.yamf.data.entity.Cinema
import java.util.concurrent.Executors

class MainRepository(private val cinemaDao: CinemaDao) {
    fun putToDb(movies: List<Cinema>){
        Executors.newSingleThreadExecutor().execute {
            cinemaDao.insertAll(movies)
        }
    }

    fun getAllFromDb(): List<Cinema> {
        return cinemaDao.getCachedMovies()
    }
}