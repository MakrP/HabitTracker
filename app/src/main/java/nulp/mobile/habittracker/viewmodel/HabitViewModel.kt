package nulp.mobile.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.util.*

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    val title = MutableLiveData<String>()
    val motivation = MutableLiveData<String>()
    val goal = MutableLiveData<Int>()
    val color = MutableLiveData<String>()
    val notificationTime = MutableLiveData<Date>()
    fun setGoal(value : Int) {
        goal.value = value
    }

}