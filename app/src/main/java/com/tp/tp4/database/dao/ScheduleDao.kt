package com.tp.tp4.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.tp.tp4.database.entities.Schedule

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule ORDER BY arrival_time")
    fun getAll(): LiveData<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName")
    fun getByStopName(stopName: String): LiveData<List<Schedule>>
}