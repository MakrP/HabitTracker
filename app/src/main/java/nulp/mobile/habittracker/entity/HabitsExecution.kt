package nulp.mobile.habittracker.entity

import androidx.room.Embedded
import androidx.room.Relation


data class HabitsExecution(
    @Embedded
    val habit: Habit,
    @Relation(
            parentColumn = "id",
            entityColumn = "habitId"
    )
    val executions : List<Execution>
) {}