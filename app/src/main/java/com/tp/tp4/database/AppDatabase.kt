package com.tp.tp4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tp.tp4.database.dao.ScheduleDao
import com.tp.tp4.database.entities.Schedule

@Database(entities = [Schedule::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun ScheduleDao() : ScheduleDao
    companion object{
        @Volatile
        private var instance : AppDatabase?=null
        fun getDatabase(context: Context) : AppDatabase{
            if(instance != null){
                return instance!!
            }
            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,"schedule_database"
            ).createFromAsset("database/bus_schedule.db").build()
            instance = db
            return instance!!
        }
    }
}