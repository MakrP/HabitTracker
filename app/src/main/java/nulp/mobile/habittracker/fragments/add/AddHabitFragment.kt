package nulp.mobile.habittracker.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_add_habit.*
import kotlinx.android.synthetic.main.fragment_add_habit.view.*
import nulp.mobile.habittracker.R
import nulp.mobile.habittracker.entity.Habit
import nulp.mobile.habittracker.viewmodel.HabitViewModel


class AddHabitFragment : Fragment() {

    private lateinit var viewModel: HabitViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_habit, container, false)
        view.detail_cancel.setOnClickListener {
            Navigation.findNavController(it).navigate(
                    R.id.action_addHabits_to_habits
            )
        }
        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        view.details_edit.setOnClickListener {
            insertHabitInDatabase();
            Navigation.findNavController(it).navigate(R.id.action_addHabits_to_habits)
        }
        return view
    }

    private fun insertHabitInDatabase() {
        val title = add_habit_title_edit_text.text.toString()
        val percentage = 0.0;
        val habit = Habit(0, title, percentage)
        viewModel.createHabit(habit)
        Toast.makeText(requireContext(), "Added to db", Toast.LENGTH_SHORT).show()
    }


}