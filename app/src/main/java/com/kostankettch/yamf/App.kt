package com.kostankettch.yamf

import android.app.Application
import com.kostankettch.yamf.data.MainRepository
import com.kostankettch.yamf.domain.Interactor

class App : Application() {
    lateinit var repo: MainRepository
    lateinit var interactor: Interactor

    override fun onCreate() {
        super.onCreate()
        instance = this
        repo = MainRepository()
        interactor = Interactor(repo)
    }

    companion object {
        lateinit var instance: App
        private set
    }
}