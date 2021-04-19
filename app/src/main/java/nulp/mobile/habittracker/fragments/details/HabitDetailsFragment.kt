package nulp.mobile.habittracker.fragments.details

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_habit.view.detail_cancel
import kotlinx.android.synthetic.main.fragment_add_habit.view.details_edit
import kotlinx.android.synthetic.main.fragment_habit_details.view.*
import nulp.mobile.habittracker.R
import nulp.mobile.habittracker.entity.Habit
import nulp.mobile.habittracker.viewmodel.HabitViewModel


class HabitDetailsFragment : Fragment() {

    private val args by navArgs<HabitDetailsFragmentArgs>()
    private lateinit var habit : Habit
    private lateinit var habitViewModel : HabitViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_habit_details, container, false)
        habit = args.habit
        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        view.details_habit_title.text = habit.title
        view.detail_cancel.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_habitDetails_to_habits)
        }
        view.details_edit.setOnClickListener {
            val action = HabitDetailsFragmentDirections.actionHabitDetailsFragmentToEditHabitFragment(habit)
            Navigation.findNavController(it).navigate(action)
        }
        view.details_delete_habit.setOnClickListener {
            val habitWasDeleted  = deleteUser()
            if(habitWasDeleted) {
                Navigation.findNavController(it).navigate(R.id.action_habitDetails_to_habits)
            }

        }
        return view;
    }



    private fun deleteUser() : Boolean {
        val alertBuilder = AlertDialog.Builder(requireContext())
        var returnValue : Boolean = false
        alertBuilder.setPositiveButton("Yes") { _,_ ->
            habitViewModel.deleteHabit(habit)
            Toast.makeText(requireContext(),"Succesfully deleted ${habit.title} from your habits",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_habitDetails_to_habits)
        }
        alertBuilder.setNegativeButton("No") { _,_ -> }
        alertBuilder.setTitle("Delete Habit ${habit.title}?")
        alertBuilder.setMessage("Are you sure you want to stop track habit ${habit.title}?")
        alertBuilder.create().show()
        return returnValue
    }
}