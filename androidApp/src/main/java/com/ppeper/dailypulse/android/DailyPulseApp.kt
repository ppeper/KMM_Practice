package com.ppeper.dailypulse.android

import android.app.Application
import com.ppeper.dailypulse.android.di.databaseModule
import com.ppeper.dailypulse.android.di.viewModelModule
import com.ppeper.dailypulse.di.sharedKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModule + viewModelModule + databaseModule

        startKoin {
            androidContext(this@DailyPulseApp)
            modules(modules)
        }
    }
}