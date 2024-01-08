package com.ppeper.dailypulse.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import ppeper.dailypulse.db.DailyPulseDB

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = DailyPulseDB.Schema,
            context = context,
            name = "DailyPulse.db"
        )
}