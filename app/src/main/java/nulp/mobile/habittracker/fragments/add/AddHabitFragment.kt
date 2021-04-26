package nulp.mobile.habittracker.fragments.add

import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import nulp.mobile.habittracker.notification.NotificationUtils
import nulp.mobile.habittracker.viewmodel.HabitViewModel
import nulp.mobile.habittracker.viewmodel.HabitsViewModel
import petrov.kristiyan.colorpicker.ColorPicker
import petrov.kristiyan.colorpicker.ColorPicker.OnChooseColorListener
import java.text.SimpleDateFormat
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

        val cal = Calendar.getInstance()

        view.remind_me_switch.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                habitViewModel.notificationTime.value = cal.time
            }
            TimePickerDialog(requireContext(), timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        habitViewModel.notificationTime.observe(viewLifecycleOwner, Observer {
            val format = SimpleDateFormat("HH-mm")
            view.choosed_time_tv.text =  format.format(it)
        })

        return view
    }



    private fun insertHabitInDatabase() {
        val habit = Habit(0, habitViewModel.title.value!!,habitViewModel.motivation.value!!,
            Date(),"#4343",
        habitViewModel.goal.value!!,0.0);
        habitsViewModel.createHabit(habit)
        NotificationUtils().setNotification(habitViewModel.notificationTime.value!!.time,requireActivity(),habit.title,habit.motivation)
        Toast.makeText(requireContext(), "Added to db", Toast.LENGTH_SHORT).show()
    }






}