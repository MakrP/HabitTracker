package nulp.mobile.habittracker.repository

import androidx.lifecycle.LiveData
import nulp.mobile.habittracker.dao.HabitDao
import nulp.mobile.habittracker.entity.Execution
import nulp.mobile.habittracker.entity.Habit
import nulp.mobile.habittracker.entity.HabitsExecution
import java.util.*

class HabitRepository(private val habitDao : HabitDao) {


    fun getAllHabits() : LiveData<List<Habit>> = habitDao.getAllHabits()

    suspend fun createHabit(habit : Habit) {
        habitDao.createHabit(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit)
    }

    suspend fun insertExecution(execution: Execution) {
        habitDao.insertExecution(execution)
    }

    suspend fun deleteExecution(execution: Execution) {
        habitDao.deleteExecution(execution)
    }

    fun getHabitExecution(habitTitle : String) : HabitsExecution {
        return habitDao.getHabitExecutionsDates(habitTitle)
    }
    fun getExecutionByHabitAndDate(habitId: Int, date : Date) : Execution {
        return habitDao.getExecutionByHabitAndDate(habitId,date)
    }

    suspend fun deleteExecutionByHabitIdAndDate(habitId: Int, date : Date) {
        habitDao.deleteExecutionByHabitIdAndDate(habitId,date)
    }

    fun getHabitExecutions(habitId : Int) : LiveData<List<Execution>> {
        return habitDao.getHabitExecutions(habitId)
    }




}
