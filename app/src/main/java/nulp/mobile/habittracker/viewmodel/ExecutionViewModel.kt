package nulp.mobile.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nulp.mobile.habittracker.db.HabitDatabase
import nulp.mobile.habittracker.entity.Execution
import nulp.mobile.habittracker.entity.Habit
import nulp.mobile.habittracker.entity.HabitsExecution
import nulp.mobile.habittracker.repository.HabitRepository
import java.util.*

class ExecutionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HabitRepository
    init {
        val habitDao = HabitDatabase.getDatabase(application).habitDao();
        repository = HabitRepository(habitDao)
    }





    fun insertExecution(execution : Execution) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertExecution(execution)
        }
    }

    fun deleteExecution(execution: Execution) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExecution(execution);
        }
    }


    fun deleteExecutionByHabitIdAndDate(habitId: Int, date : Date) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExecutionByHabitIdAndDate(habitId, date)
        }
    }

    fun getHabitExecution(habitTitle : String) : HabitsExecution {
        return repository.getHabitExecution(habitTitle)
    }

    fun getExecutionByHabitAndDate(habitId: Int, date : Date) : Execution {
        return repository.getExecutionByHabitAndDate(habitId,date)
    }

    fun getHabitExecutions(habitId : Int) : LiveData<List<Execution>> {
        return repository.getHabitExecutions(habitId)
    }




}