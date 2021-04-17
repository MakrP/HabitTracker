package nulp.mobile.habittracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nulp.mobile.habittracker.R
import nulp.mobile.habittracker.entity.Habit

class HabitAdapter() : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {
    class HabitViewHolder(view : View) : RecyclerView.ViewHolder(view) {
         val habitTitle : TextView = view.findViewById<TextView>(R.id.habit_title)
         val donePercent : TextView = view.findViewById<TextView>(R.id.done_percent)
    }

    private var habitsList : List<Habit> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.habit_item, parent, false)

        return HabitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return habitsList.size
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.habitTitle.text = habitsList[position].title
        holder.donePercent.text = habitsList[position].exec.toString() + "%"
    }

    fun setData(habits : List<Habit>) {
        habitsList = habits
        notifyDataSetChanged()
    }
}