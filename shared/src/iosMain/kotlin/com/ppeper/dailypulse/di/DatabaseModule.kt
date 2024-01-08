package com.ppeper.dailypulse.di

import com.ppeper.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module
import ppeper.dailypulse.db.DailyPulseDB

val databaseModule = module {
    single { DatabaseDriverFactory().createDriver() }
    single { DailyPulseDB(get()) }
}