package nulp.mobile.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nulp.mobile.habittracker.db.HabitDatabase
import nulp.mobile.habittracker.entity.Habit
import nulp.mobile.habittracker.repository.HabitRepository

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HabitRepository
    private val allHabits : LiveData<List<Habit>>

    init {
        val habitDao = HabitDatabase.getDatabase(application).habitDao();
        repository = HabitRepository(habitDao)
        allHabits = repository.getAllHabits()
    }

    fun getHabits() = repository.getAllHabits()

    fun createHabit(habit : Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createHabit(habit)
        }
    }
}