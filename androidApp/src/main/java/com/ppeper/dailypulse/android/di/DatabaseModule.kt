package com.ppeper.dailypulse.android.di

import com.ppeper.dailypulse.db.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ppeper.dailypulse.db.DailyPulseDB

val databaseModule = module {
    single { DatabaseDriverFactory(androidContext()).createDriver() }
    single { DailyPulseDB(get()) }
}