//package nulp.mobile.habittracker.entity
//
//import androidx.room.Embedded
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import androidx.room.Relation
//import java.util.*
//
//@Entity(tableName = "habit_execution")
//data class HabitExecution(
//        @PrimaryKey
//        val habitExecutionId: Int,
//        @Embedded val habit : Habit,
//        @Relation(
//                parentColumn = "id",
//                entityColumn = "habitId"
//        )
//        val day : Date
//) {
//}