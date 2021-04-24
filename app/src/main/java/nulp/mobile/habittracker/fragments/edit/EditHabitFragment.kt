package nulp.mobile.habittracker.fragments.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_edit_habit.*
import kotlinx.android.synthetic.main.fragment_edit_habit.view.*
import nulp.mobile.habittracker.R
import nulp.mobile.habittracker.entity.Habit
import nulp.mobile.habittracker.viewmodel.HabitsViewModel
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random

class EditHabitFragment : Fragment() {

    private val args by navArgs<EditHabitFragmentArgs>()
    private lateinit var habit : Habit
    private lateinit var habitViewModel : HabitsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_habit, container, false)
        habit = args.habit
        habitViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)
        view.edit_habit_title_edit_text.setText(habit.title)
        view.edit_back_text_view.setOnClickListener {
            val action = EditHabitFragmentDirections.actionEditHabitToHabitDetails(habit)
            Navigation.findNavController(it).navigate(action)
        }
        view.edit_save_text_view.setOnClickListener {
            val updatedHabit = updateHabit()
            val action = EditHabitFragmentDirections.actionEditHabitToHabitDetails(updatedHabit)
            Navigation.findNavController(it).navigate(action)
        }
        return view
    }


    private fun updateHabit() : Habit {
        val newTitle = edit_habit_title_edit_text.text.toString()
        val newHabit = Habit(habit.id,newTitle,habit.motivation,habit.startDate,habit.color,habit.goal,(Random.nextDouble() * 10).roundToInt().toDouble())
        habitViewModel.updateHabit(newHabit)
        return newHabit
    }


}