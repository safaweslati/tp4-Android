package com.tp.tp4.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tp.tp4.database.dao.ScheduleDao
import com.tp.tp4.database.entities.Schedule

class BusScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {
    fun fullSchedule(): LiveData<List<Schedule>> = scheduleDao.getAll()

    fun scheduleForStopName(name: String): LiveData<List<Schedule>> =
        scheduleDao.getByStopName(name)
}