package com.kostankettch.yamf.di.modules

import android.content.Context
import com.kostankettch.yamf.data.DatabaseHelper
import com.kostankettch.yamf.data.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseHelper(context: Context) = DatabaseHelper(context)

    @Provides
    @Singleton
    fun provideRepositories(databaseHelper: DatabaseHelper) = MainRepository(databaseHelper)
}