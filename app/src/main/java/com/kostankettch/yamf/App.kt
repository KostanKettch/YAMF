package com.kostankettch.yamf

import android.app.Application
import com.kostankettch.yamf.di.AppComponent
import com.kostankettch.yamf.di.DaggerAppComponent

class App : Application() {
    lateinit var dagger: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}