package com.kostankettch.yamf

import android.app.Application
import com.kostankettch.yamf.di.AppComponent
import com.kostankettch.yamf.di.DaggerAppComponent
import com.kostankettch.yamf.di.modules.DatabaseModule
import com.kostankettch.yamf.di.modules.DomainModule
import com.kostankettch.yamf.di.modules.RemoteModule

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}