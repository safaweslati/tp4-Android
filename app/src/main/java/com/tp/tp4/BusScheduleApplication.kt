package com.tp.tp4

import android.app.Application
import com.tp.tp4.database.AppDatabase

class BusScheduleApplication : Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this) }
}