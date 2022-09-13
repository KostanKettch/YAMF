package com.kostankettch.yamf.di.modules

import android.content.Context
import androidx.room.Room
import com.kostankettch.yamf.data.MainRepository
import com.kostankettch.yamf.data.dao.CinemaDao
import com.kostankettch.yamf.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideCinemaDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "cinema_db"
        ).build().cinemaDao()

    @Provides
    @Singleton
    fun provideRepositories(cinemaDao: CinemaDao) = MainRepository(cinemaDao)
}