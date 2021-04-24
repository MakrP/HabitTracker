package nulp.mobile.habittracker.fragments.add.adddetalis

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_goal_choosing.*
import kotlinx.android.synthetic.main.fragment_goal_choosing.view.*
import nulp.mobile.habittracker.R
import nulp.mobile.habittracker.databinding.FragmentGoalChoosingBinding
import nulp.mobile.habittracker.viewmodel.HabitViewModel

class GoalChoosingFragment : Fragment() {

    private val habitViewModel: HabitViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentGoalChoosingBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        binding.habitViewModel = habitViewModel
        habitViewModel.goal.observe(viewLifecycleOwner, Observer {
            hideAllChecks()
            when(it) {
                30 -> choose_30_days.visibility = View.VISIBLE
                90 -> choose_90_days.visibility = View.VISIBLE
                180 -> choose_180_days.visibility = View.VISIBLE
                365 -> choose_365_days.visibility = View.VISIBLE
            }
        })


        view.back_arrow.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_goalChoosingFragment_to_addHabitsFragment)
        }

        return view
    }

    private fun hideAllChecks() {
        choose_30_days.visibility = View.GONE;
        choose_90_days.visibility = View.GONE;
        choose_180_days.visibility = View.GONE;
        choose_365_days.visibility = View.GONE;
    }

}