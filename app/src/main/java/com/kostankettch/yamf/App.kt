package com.kostankettch.yamf

import android.app.Application
import com.kostankettch.yamf.di.DI
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(listOf(DI.mainModule))
        }
    }
}