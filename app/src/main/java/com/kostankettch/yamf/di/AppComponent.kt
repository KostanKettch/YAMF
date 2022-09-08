package com.kostankettch.yamf.di

import com.kostankettch.yamf.di.modules.DatabaseModule
import com.kostankettch.yamf.di.modules.DomainModule
import com.kostankettch.yamf.di.modules.RemoteModule
import com.kostankettch.yamf.viewmodel.HomeFragmentViewModel
import com.kostankettch.yamf.viewmodel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}