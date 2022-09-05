package com.kostankettch.yamf.di.modules

import com.kostankettch.yamf.data.MainRepository
import com.kostankettch.yamf.data.TmdbApi
import com.kostankettch.yamf.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(repo = repository, retrofitService = tmdbApi)
}