package com.olzumzum.booklib.ui.listbydata

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.databinding.FragmentBookByDateListBinding
import com.olzumzum.booklib.viewmodel.BookViewModel
import com.olzumzum.booklib.viewmodel.CategoriesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_book_by_date_list.*
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
        //биндинг данных во вью
        val view = binding.root
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        val adapter = BookRecyclerViewAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.books_by_date_lsit)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = adapter

        viewModel.getBooks().observe(viewLifecycleOwner, Observer {books ->
            adapter.update(books)
        })

        viewModel.getErrorMessage().observe(viewLifecycleOwner, Observer {message ->
            showErrorMessage(message)
        })

        return view
    }

    fun showErrorMessage(idResource: Int){
        Snackbar.make(fragment_book_by_date_layout, getString(idResource), Snackbar.LENGTH_LONG).show()
    }




    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = BookByDateFragment()
    }

}

/**
 * Загрузка изображений
 */
@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String){
    Picasso.get()
        .load(url)
        .placeholder(R.color.colorPrimaryDark)
        .error(R.color.colorAccent)
        .fit()
        .into(imageView)
}