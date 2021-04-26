package nulp.mobile.habittracker.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.habit_item.view.*
import nulp.mobile.habittracker.R
import nulp.mobile.habittracker.entity.Execution
import nulp.mobile.habittracker.entity.Habit
import nulp.mobile.habittracker.fragments.show.HabitsFragmentDirections
import nulp.mobile.habittracker.viewmodel.ExecutionViewModel
import nulp.mobile.habittracker.viewmodel.HabitsViewModel
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.math.roundToInt


class HabitAdapter(val context : Context) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {
    class HabitViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val habitTitle : TextView = view.findViewById<TextView>(R.id.habit_title)
        val donePercent : TextView = view.findViewById<TextView>(R.id.done_percent)
        val share : ImageView = view.findViewById<ImageView>(R.id.share)
        var doneIcon = AppCompatResources.getDrawable(view.context, R.drawable.done_icon)
        var undoneIcon = AppCompatResources.getDrawable(view.context, R.drawable.ic_baseline_undone_mark)
        val doneToday = view.findViewById<ImageView>(R.id.done_today)
        val doneTomorrow = view.findViewById<ImageView>(R.id.done_tomorrow)
        val done2DaysAgo = view.findViewById<ImageView>(R.id.done_2_days_ago)
        val done3DaysAgo = view.findViewById<ImageView>(R.id.done_3_days_ago)
        val done4DaysAgo = view.findViewById<ImageView>(R.id.done_4_days_ago)
    }

    private var habitsList : List<Habit> = emptyList()
    private lateinit var topCurrentHabit : Habit
    val habitsViewModel = ViewModelProvider(context as FragmentActivity).get(HabitsViewModel::class.java)
    val executionViewModel = ViewModelProvider(context as FragmentActivity).get(ExecutionViewModel::class.java)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.habit_item, parent, false)

        return HabitViewHolder(view)
    }

    override fun getItemCount(): Int {
        return habitsList.size
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val currentHabit = habitsList[position]
        holder.habitTitle.text = currentHabit.title
        holder.donePercent.text = currentHabit.exec.toString() + "%"

        holder.doneToday.setOnClickListener {
            if(it.background == holder.doneIcon) {
                it.background = holder.undoneIcon
                currentHabit.exec -= 4;
                habitsViewModel.updateHabit(currentHabit)
                holder.donePercent.text = currentHabit.exec.toString() + "%"
                executionViewModel.deleteExecutionByHabitIdAndDate(currentHabit.id,Date())
            } else {
                currentHabit.exec += 4;
                habitsViewModel.updateHabit(currentHabit)
                holder.donePercent.text = currentHabit.exec.toString() + "%"
                it.background = holder.doneIcon
                executionViewModel.insertExecution(Execution(0,currentHabit.id,Date()))
            }
        }


        holder.doneTomorrow.setOnClickListener {
            val currentDate = LocalDate.now()
            val convertedDate = convertToDateViaInstant(currentDate.minusDays(1))
            if(it.background == holder.doneIcon) {
                it.background = holder.undoneIcon
                currentHabit.exec -= 4;
                habitsViewModel.updateHabit(currentHabit)
                holder.donePercent.text = currentHabit.exec.toString() + "%"
                executionViewModel.deleteExecutionByHabitIdAndDate(currentHabit.id,convertedDate!!)
            } else {
                executionViewModel.insertExecution(Execution(0,currentHabit.id, convertedDate!!))
                currentHabit.exec += 4;
                habitsViewModel.updateHabit(currentHabit)
                holder.donePercent.text = currentHabit.exec.toString() + "%"
                it.background = holder.doneIcon
            }
        }

      holder.done2DaysAgo.setOnClickListener {
          val currentDate = LocalDate.now()
          val convertedDate = convertToDateViaInstant(currentDate.minusDays(2))
          if(it.background == holder.doneIcon) {
              it.background = holder.undoneIcon
              currentHabit.exec -= 4;
              habitsViewModel.updateHabit(currentHabit)
              holder.donePercent.text = currentHabit.exec.toString() + "%"
              executionViewModel.deleteExecutionByHabitIdAndDate(currentHabit.id,convertedDate!!)
          } else {
              executionViewModel.insertExecution(Execution(0,currentHabit.id, convertedDate!!))
              currentHabit.exec += 4;
              habitsViewModel.updateHabit(currentHabit)
              holder.donePercent.text = currentHabit.exec.toString() + "%"
              it.background = holder.doneIcon
          }
        }


        holder.done3DaysAgo.setOnClickListener {
            val currentDate = LocalDate.now()
            val convertedDate = convertToDateViaInstant(currentDate.minusDays(3))
            if(it.background == holder.doneIcon) {
                it.background = holder.undoneIcon
                currentHabit.exec -= 4;
                habitsViewModel.updateHabit(currentHabit)
                executionViewModel.deleteExecutionByHabitIdAndDate(currentHabit.id,convertedDate!!)
                holder.donePercent.text = currentHabit.exec.toString() + "%"
            } else {
                currentHabit.exec += 4;
                habitsViewModel.updateHabit(currentHabit)
                holder.donePercent.text = currentHabit.exec.toString() + "%"
                executionViewModel.insertExecution(Execution(0,currentHabit.id, convertedDate!!))
                it.background = holder.doneIcon
            }
        }


        holder.done4DaysAgo.setOnClickListener {
            val currentDate = LocalDate.now()
            val convertedDate = convertToDateViaInstant(currentDate.minusDays(4))
            if(it.background == holder.doneIcon) {
                it.background = holder.undoneIcon
                habitsViewModel.updateHabit(currentHabit)
                currentHabit.exec -= 4;
                executionViewModel.deleteExecutionByHabitIdAndDate(currentHabit.id,convertedDate!!)
                holder.donePercent.text = currentHabit.exec.toString() + "%"
            } else {
                it.background = holder.doneIcon
                executionViewModel.insertExecution(Execution(0,currentHabit.id, convertedDate!!))
                currentHabit.exec += 4;
                habitsViewModel.updateHabit(currentHabit)
                holder.donePercent.text = currentHabit.exec.toString() + "%"
            }
        }




        holder.share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "I do my habit ${currentHabit.title} for ${currentHabit.exec}%" )
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
             context.startActivity(shareIntent)
        }

        holder.itemView.habit_item.setOnClickListener {
            val action = HabitsFragmentDirections.actionHabitsToHabitDetails(habitsList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(habits : List<Habit>) {
        habitsList = habits
        notifyDataSetChanged()
    }

    fun convertToDateViaInstant(dateToConvert: LocalDate): Date? {
        return Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant())
    }



}