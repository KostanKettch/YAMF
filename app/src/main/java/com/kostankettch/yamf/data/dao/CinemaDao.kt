package com.kostankettch.yamf.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kostankettch.yamf.data.entity.Cinema
import kotlinx.coroutines.flow.Flow

@Dao
interface CinemaDao {
    @Query ("SELECT * FROM cached_films")
    fun getCachedMovies(): Flow<List<Cinema>>

    @Insert
    fun insertAll(list: List<Cinema>)
}