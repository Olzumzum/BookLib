package com.olzumzum.booklib.ui.listbydata

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.databinding.FragmentBookByDateListBinding
import com.olzumzum.booklib.model.pojo.BookX
import com.olzumzum.booklib.ui.book_full_info.BookFullInfoFragment
import com.olzumzum.booklib.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_book_by_date_list.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class BookByDateFragment : Fragment(), NavigatorBooks {

    @Inject
    lateinit var viewModel: BookViewModel

    private var navController: NavController? = null

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


        val recyclerView = view.findViewById<RecyclerView>(R.id.books_by_date_lsit)
        recyclerView.addItemDecoration(RecyclerDivider(requireContext()))

        binding.booksByDateLsit.layoutManager = LinearLayoutManager(context)


        //обработка нажатия на элемент списка
        viewModel.setNavigatorBooks(this)

        if (savedInstanceState == null)
            viewModel.getBooks()?.observe(viewLifecycleOwner, Observer { books ->
                binding.booksByDateLsit.adapter = BookRecyclerViewAdapter(books, viewModel)
            })

        viewModel.getErrorMessage().observe(viewLifecycleOwner, Observer { message ->
            showErrorMessage(message)
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //навигация - для открытия полной информации по элементу списка
        navController = Navigation.findNavController(view)
    }

    private fun showErrorMessage(idResource: Int) {
        Snackbar.make(fragment_book_by_date_layout, getString(idResource), Snackbar.LENGTH_LONG)
            .show()
    }


    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = BookByDateFragment()
    }

    override fun onItemClicked(book: LiveData<BookX>?) {
        navController?.navigate(R.id.action_bookByDateFragment_to_bookFullInfoFragment)
    }

}



