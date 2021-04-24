package nulp.mobile.habittracker.fragments.add.adddetalis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_regularity_choosing.view.*
import nulp.mobile.habittracker.R


class RegularityChoosingFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_regularity_choosing, container, false)
        view.choose_regularity_back_arrow.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_regularityChoosingFragment_to_addHabitsFragment)
        }
        return view
    }

}