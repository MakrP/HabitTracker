package nulp.mobile.habittracker.fragments.add

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_add_habit.*
import kotlinx.android.synthetic.main.fragment_add_habit.view.*
import nulp.mobile.habittracker.R
import nulp.mobile.habittracker.databinding.FragmentAddHabitBinding
import nulp.mobile.habittracker.entity.Habit
import nulp.mobile.habittracker.viewmodel.HabitViewModel
import nulp.mobile.habittracker.viewmodel.HabitsViewModel
import petrov.kristiyan.colorpicker.ColorPicker
import petrov.kristiyan.colorpicker.ColorPicker.OnChooseColorListener
import java.util.*


//import java.util.*


class AddHabitFragment : Fragment() {

    private lateinit var habitsViewModel: HabitsViewModel
    private val habitViewModel: HabitViewModel by activityViewModels()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddHabitBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        binding.habitViewModel = habitViewModel
        if(habitViewModel.color.value == null) {
            habitViewModel.color.value = Color.RED.toString()
        }
        habitsViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)
        habitViewModel.color.observe(viewLifecycleOwner,Observer{
            Log.i("color",it)
            top_container.setBackgroundColor(it.toInt())
            choosing_color_view.setBackgroundColor(it.toInt())
        })
        view.detail_cancel.setOnClickListener {
            Navigation.findNavController(it).navigate(
                    R.id.action_addHabits_to_habits
            )
        }
        view.details_edit.setOnClickListener {
            insertHabitInDatabase();
            Navigation.findNavController(it).navigate(R.id.action_addHabits_to_habits)
        }
        view.regularityContainer.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_addHabits_to_regularityChoosing)
        }

        view.goalConteiner.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_addHabits_to_goalChoosing)
        }

        view.color_choosing_container.setOnClickListener {
            val colorPicker = ColorPicker(activity)
            colorPicker.show()
            colorPicker.setOnChooseColorListener(object : OnChooseColorListener {
                override fun onChooseColor(position: Int, color: Int) {
                    habitViewModel.color.value = color.toString()
                }

                override fun onCancel() {
                    // put code
                }
            })
        }
        return view
    }



    private fun insertHabitInDatabase() {
        val habit = Habit(0, habitViewModel.title.value!!,habitViewModel.motivation.value!!,
            Date(),"#4343",
        habitViewModel.goal.value!!,0.0);
        habitsViewModel.createHabit(habit)
        Toast.makeText(requireContext(), "Added to db", Toast.LENGTH_SHORT).show()
    }




}