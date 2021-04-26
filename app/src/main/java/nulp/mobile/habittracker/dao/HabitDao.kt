package nulp.mobile.habittracker.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import nulp.mobile.habittracker.entity.Execution
import nulp.mobile.habittracker.entity.Habit
import nulp.mobile.habittracker.entity.HabitsExecution
import java.util.*


@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createHabit(habit : Habit)

    @Query("SELECT * FROM habits")
    fun getAllHabits() : LiveData<List<Habit>>

    @Update
    suspend fun updateHabit(habit : Habit)

    @Delete
    suspend fun deleteHabit(habit : Habit)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExecution(execution : Execution)

    @Delete
    suspend fun deleteExecution(execution : Execution)

    @Query("SELECT * from habits where title = :habitTitle")
    fun getHabitExecutionsDates(habitTitle : String) : HabitsExecution

    @Query("SELECT * FROM habit_execution where habitId = :habitId and date = :date")
    fun getExecutionByHabitAndDate(habitId: Int, date : Date) : Execution

    @Query("delete from habit_execution where habitId = :habitId and date = :date ")
    suspend fun deleteExecutionByHabitIdAndDate(habitId: Int, date : Date)

    @Query("SElECT * from habit_execution where habitId = :habitId ")
    fun getHabitExecutions(habitId : Int) : LiveData<List<Execution>>
}