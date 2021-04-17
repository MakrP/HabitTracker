package nulp.mobile.habittracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nulp.mobile.habittracker.dao.HabitDao
import nulp.mobile.habittracker.entity.Habit

@Database(entities = [Habit::class],version = 2,exportSchema = false)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao() : HabitDao

    companion object {
        @Volatile
        private var INSTANCE : HabitDatabase? = null

        fun getDatabase(context : Context) : HabitDatabase {
            val tempInstance = INSTANCE;
            if(tempInstance != null) {
                return tempInstance;
            }
            synchronized(this) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java, "habit_tracker_db"
                ).build()
                INSTANCE = db;
                return db
            }
        }
    }
}