package nulp.mobile.habittracker.repository

import androidx.lifecycle.LiveData
import nulp.mobile.habittracker.dao.HabitDao
import nulp.mobile.habittracker.entity.Habit

class HabitRepository(private val habitDao : HabitDao) {


    fun getAllHabits() : LiveData<List<Habit>> = habitDao.getAllHabits()

    suspend fun createHabit(habit : Habit) {
        habitDao.createHabit(habit)
    }
}
