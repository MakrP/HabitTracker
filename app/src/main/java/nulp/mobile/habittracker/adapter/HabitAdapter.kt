package nulp.mobile.habittracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.habit_item.view.*
import nulp.mobile.habittracker.R
import nulp.mobile.habittracker.entity.Habit
import nulp.mobile.habittracker.fragments.show.HabitsFragmentDirections

class HabitAdapter() : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {
    class HabitViewHolder(view : View) : RecyclerView.ViewHolder(view) {
         val habitTitle : TextView = view.findViewById<TextView>(R.id.habit_title)
         val donePercent : TextView = view.findViewById<TextView>(R.id.done_percent)
    }

    private var habitsList : List<Habit> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.habit_item, parent, false)

        val listener : (v : View) -> Unit = {it.setBackgroundResource(R.drawable.done_icon)}
        view.done1.setOnClickListener(listener)
        view.done2.setOnClickListener(listener)
        view.done3.setOnClickListener(listener)
        view.done4.setOnClickListener(listener)
        view.done5.setOnClickListener(listener)
        return HabitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return habitsList.size
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.habitTitle.text = habitsList[position].title
        holder.donePercent.text = habitsList[position].exec.toString() + "%"
        holder.itemView.habit_item.setOnClickListener {
            val action = HabitsFragmentDirections.actionHabitsToHabitDetails(habitsList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(habits : List<Habit>) {
        habitsList = habits
        notifyDataSetChanged()
    }
}