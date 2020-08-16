package com.olzumzum.booklib.ui.listbydata

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.model.Results
import com.olzumzum.booklib.ui.listbydata.dummy.DummyContent
import com.olzumzum.booklib.viewmodel.BookViewModel
import java.util.Observer
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
        val view = inflater.inflate(R.layout.fragment_book_by_date, container, false)

//        // Set the adapter
//        if (view is RecyclerView) {
//            with(view) {
//                layoutManager = when {
//                    columnCount <= 1 -> LinearLayoutManager(context)
//                    else -> GridLayoutManager(context, columnCount)
//                }
//            }
//        }

        val bestsellersDate: TextView = view.findViewById(R.id.bestsellers_date)
        val books: TextView = view.findViewById(R.id.books)
        val corrections: TextView = view.findViewById(R.id.corrections)
        val displayName: TextView = view.findViewById(R.id.display_name)
        val listName: TextView = view.findViewById(R.id.list_name)
        val listNameEncoded: TextView = view.findViewById(R.id.list_name_encoded)
        val nextPublishedDate: TextView = view.findViewById(R.id.next_published_date)
        val normal_list_ends_at: TextView = view.findViewById(R.id.normal_list_ends_at)
        val previous_published_date: TextView = view.findViewById(R.id.previous_published_date)
        val published_date: TextView = view.findViewById(R.id.published_date)
        val published_date_description: TextView =
            view.findViewById(R.id.published_date_description)
        val updated: TextView = view.findViewById(R.id.updated)

        viewModel.getResults().observe(viewLifecycleOwner, androidx.lifecycle.Observer { results ->
            bestsellersDate.text = "${results.bestsellers_date}"
            books.text = "${results.books.size}"
            corrections.text = "${results.corrections}"
            displayName.text = "${results.display_name}"
            listName.text = "${results.list_name}"
            listNameEncoded.text = "${results.list_name_encoded}"
            nextPublishedDate.text = "${results.next_published_date}"
            normal_list_ends_at.text = "${results.normal_list_ends_at}"
            previous_published_date.text = "${results.previous_published_date}"
            published_date.text = "${results.published_date}"
            published_date_description.text = "${results.published_date_description}"
            updated.text = "${results.updated}"
        })

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = BookByDateFragment()
    }

}