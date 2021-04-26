package nulp.mobile.habittracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nulp.mobile.habittracker.dao.HabitDao
import nulp.mobile.habittracker.db.convertor.DateConverter
import nulp.mobile.habittracker.entity.Execution
//import nulp.mobile.habittracker.dao.HabitExecutionDao
import nulp.mobile.habittracker.entity.Habit
//import nulp.mobile.habittracker.entity.HabitExecution

@Database(entities = [Habit::class, Execution::class],version = 1,exportSchema = false)
@TypeConverters(DateConverter::class)
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