package com.kostankettch.yamf.di.modules

import com.kostankettch.yamf.data.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRepositories() = MainRepository()
}