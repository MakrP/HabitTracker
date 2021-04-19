package nulp.mobile.habittracker.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "habits")
data class Habit(
  @PrimaryKey(autoGenerate = true)
  val id : Int,
  val title : String,
  val exec : Double
) : Parcelable