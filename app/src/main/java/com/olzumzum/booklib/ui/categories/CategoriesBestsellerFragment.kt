package com.olzumzum.booklib.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.olzumzum.booklib.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesBestsellerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesBestsellerFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories_bestseller, container, false)

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = CategoriesBestsellerFragment()
    }
}