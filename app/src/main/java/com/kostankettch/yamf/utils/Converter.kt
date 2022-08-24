package com.kostankettch.yamf.utils

import com.kostankettch.yamf.domain.Cinema
import com.kostankettch.yamf.data.Entity.TmdbMovie

object Converter {
    fun convertApiListToDtoList(list: List<TmdbMovie>?): List<Cinema> {
        val result = mutableListOf<Cinema>()
        list?.forEach {
            result.add(
                Cinema(
                    title = it.title,
                    poster = it.posterPath,
                    description = it.overview,
                    rating = it.voteAverage,
                    isFavorite = false
                )
            )
        }
        return result
    }
}