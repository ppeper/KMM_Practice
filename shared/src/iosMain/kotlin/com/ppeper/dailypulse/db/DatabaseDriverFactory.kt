package com.ppeper.dailypulse.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import ppeper.dailypulse.db.DailyPulseDB

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema = DailyPulseDB.Schema,
            name = "DailyPulse.db"
        )
}