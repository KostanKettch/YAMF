package com.kostankettch.yamf.di.modules

import android.content.Context
import com.kostankettch.yamf.data.MainRepository
import com.kostankettch.yamf.data.TmdbApi
import com.kostankettch.yamf.data.preferences.PreferenceProvider
import com.kostankettch.yamf.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule(val context: Context) {
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun providePreferences(context: Context) = PreferenceProvider(context)

    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi, preferenceProvider: PreferenceProvider) = Interactor(repo = repository, retrofitService = tmdbApi, preferences = preferenceProvider)
}