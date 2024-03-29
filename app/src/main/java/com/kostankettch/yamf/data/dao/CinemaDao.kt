package com.kostankettch.yamf.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kostankettch.yamf.data.entity.Cinema
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

@Dao
interface CinemaDao {
    @Query ("SELECT * FROM cached_films")
    fun getCachedMovies(): Observable<List<Cinema>>

    @Insert
    fun insertAll(list: List<Cinema>)
}