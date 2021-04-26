package nulp.mobile.habittracker.fragments.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nulp.mobile.habittracker.R
import nulp.mobile.habittracker.adapter.HabitAdapter
import nulp.mobile.habittracker.viewmodel.HabitsViewModel


class HabitsListFragment : Fragment() {

    private lateinit var hViewModel : HabitsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_habit_list, container, false)
        val habitsRecycleView = view.findViewById<RecyclerView>(R.id.habitsRycycleView);
        val habitAdapter = HabitAdapter(this.requireContext())
        hViewModel = ViewModelProvider(this).get(HabitsViewModel::class.java)
        hViewModel.getHabits().observe(viewLifecycleOwner, Observer{
            habitAdapter.setData(it)
        })
        habitsRecycleView.adapter = habitAdapter
        habitsRecycleView.layoutManager = LinearLayoutManager(this.requireContext())
        return view
    }

}