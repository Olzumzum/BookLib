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
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
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
        val view = inflater.inflate(R.layout.fragment_book_by_date_list, container, false)

        val adapter = BookRecyclerViewAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.books_by_date_lsit)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        val bestsellersDate: TextView = view.findViewById(R.id.bestsellers_date)

        val displayName: TextView = view.findViewById(R.id.display_name)

        val nextPublishedDate: TextView = view.findViewById(R.id.next_published_date)
        val publishedDateDescription: TextView = view.findViewById(R.id.published_date_description)
        val published_date: TextView = view.findViewById(R.id.published_date)
        val updated: TextView = view.findViewById(R.id.updated)

        viewModel.getResults().observe(viewLifecycleOwner, androidx.lifecycle.Observer { results ->
            bestsellersDate.text = "${results.bestsellers_date}"
            displayName.text = "${results.display_name}"
            nextPublishedDate.text = "${results.next_published_date}"
            published_date.text = "${results.published_date}"
            publishedDateDescription.text = "${results.published_date_description}"
            updated.text = "${results.updated}"
            adapter.update(results.books)
        })

        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = BookByDateFragment()
    }

}