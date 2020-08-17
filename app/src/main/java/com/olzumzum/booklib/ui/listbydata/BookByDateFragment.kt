package com.olzumzum.booklib.ui.listbydata

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.databinding.FragmentBookByDateBinding
import com.olzumzum.booklib.databinding.FragmentBookByDateListBinding
import com.olzumzum.booklib.viewmodel.BookViewModel
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class BookByDateFragment : Fragment() {

    @Inject
    lateinit var viewModel: BookViewModel
    private var columnCount = 1

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

        val binding: FragmentBookByDateListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_book_by_date_list,
            container,
            false
        )

        val view = binding.root




        val adapter = BookRecyclerViewAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.books_by_date_lsit)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = adapter



        viewModel.getResults().observe(viewLifecycleOwner, androidx.lifecycle.Observer { results ->
            binding.results = results
            adapter.update(results.books)
        })

        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = BookByDateFragment()
    }

}