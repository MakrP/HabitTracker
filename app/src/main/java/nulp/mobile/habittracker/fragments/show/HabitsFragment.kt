package nulp.mobile.habittracker.fragments.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_habits.view.*
import nulp.mobile.habittracker.R


class HabitsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_habits, container, false)
        view.addHabitImage.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_habits_to_addHabits)
        }
        return view
    }



}