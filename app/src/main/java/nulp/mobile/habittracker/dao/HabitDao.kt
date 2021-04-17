package nulp.mobile.habittracker.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import nulp.mobile.habittracker.entity.Habit


@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createHabit(habit : Habit)

    @Query("SELECT * FROM habits")
    fun getAllHabits() : LiveData<List<Habit>>


}