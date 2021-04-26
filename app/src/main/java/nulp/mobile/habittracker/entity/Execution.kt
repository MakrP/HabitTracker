package nulp.mobile.habittracker.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*

@Entity(tableName = "habit_execution")
data class Execution(
        @PrimaryKey(autoGenerate = true)
        val executionId : Int,
        val habitId : Int,
        val date: Date

) {
}