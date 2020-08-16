package com.olzumzum.booklib.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olzumzum.booklib.R

class CategoriesBestsellerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories_bestseller, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.categories_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = CategoriesAdapter()

        return view
    }

    companion object {
        private val INSTANCE: CategoriesBestsellerFragment? = null
        @JvmStatic
        fun newInstance(): CategoriesBestsellerFragment =CategoriesBestsellerFragment()

    }
}