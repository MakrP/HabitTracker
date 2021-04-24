package nulp.mobile.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    val title = MutableLiveData<String>()
    val motivation = MutableLiveData<String>()
    val goal = MutableLiveData<Int>()
    val color = MutableLiveData<String>()
    val repeated = MutableLiveData<Int>()

    fun setGoal(value : Int) {
        goal.value = value
    }

}