package com.kostankettch.yamf.domain

import com.kostankettch.yamf.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getMoviesDB(): List<Cinema> = repo.moviesDB
}