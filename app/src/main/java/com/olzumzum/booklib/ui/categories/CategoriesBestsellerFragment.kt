package com.olzumzum.booklib.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.viewmodel.BookViewModel
import javax.inject.Inject

class CategoriesBestsellerFragment : Fragment() {
    @Inject
    lateinit var viewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).getAppComponent().activitySubComponentBuilder()
            .with(activity = activity as AppCompatActivity)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_categories_bestseller, container, false)

        val adapter = CategoriesAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.categories_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        viewModel.getAllBook().observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })
        return view
    }

    companion object {
        private val INSTANCE: CategoriesBestsellerFragment? = null
        @JvmStatic
        fun newInstance(): CategoriesBestsellerFragment =CategoriesBestsellerFragment()

    }
}