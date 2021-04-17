package nulp.mobile.habittracker.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
  @PrimaryKey(autoGenerate = true)
  val id : Int,
  val title : String,
  val exec : Double
)