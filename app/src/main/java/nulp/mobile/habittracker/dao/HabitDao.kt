package nulp.mobile.habittracker.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import nulp.mobile.habittracker.entity.Habit


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

}