package nulp.mobile.habittracker.fragments.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_habits.view.*
import nulp.mobile.habittracker.R
import nulp.mobile.habittracker.databinding.FragmentHabitsBinding
import nulp.mobile.habittracker.viewmodel.HabitsViewModel
import java.time.LocalDate


class HabitsFragment : Fragment() {

    private lateinit var habitViewModel : HabitsViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        habitViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)
        val habitsBinding = FragmentHabitsBinding.inflate(layoutInflater, container, false)
//        val view = inflater.inflate(R.layout.fragment_habits, container, false)
        val view = habitsBinding.root
        habitsBinding.currentDate = LocalDate.now()
        view.addHabitImage.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_habits_to_addHabits)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val habitList = habitViewModel.getHabits().observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()) {
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<HabitsListFragment>(R.id.habits_fragment_container)
                }
            } else {
                childFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<NoHabitsFragment>(R.id.habits_fragment_container)
                }
            }
        })

    }



}