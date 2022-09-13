package com.kostankettch.yamf.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kostankettch.yamf.data.dao.CinemaDao
import com.kostankettch.yamf.data.entity.Cinema

@Database(entities = [Cinema::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cinemaDao(): CinemaDao
}