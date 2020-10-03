package com.olzumzum.booklib.ui.listbydata

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearSnapHelper
import com.google.android.material.snackbar.Snackbar
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.databinding.FragmentBookByDateListBinding
import com.olzumzum.booklib.model.pojo.BookX
import com.olzumzum.booklib.utils.changeDateFormat
import com.olzumzum.booklib.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_book_by_date_list.*
import java.util.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class BookByDateFragment : Fragment(), NavigatorBooks {


    @Inject
    lateinit var viewModel: BookViewModel

    //для перемещения между фрагментами
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

        //задать декоратор адаптеру
        binding.booksRecycler.addItemDecoration(RecyclerDivider(requireContext()))
        binding.booksRecycler.layoutManager = LinearLayoutManager(context)

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.booksRecycler)

        //скрытие окна со сводной информацией о списке бестселлеров
        hideElement(binding.infoFragment?.infoLayout, binding.booksRecycler)

        //обработка нажатия на элемент списка
        viewModel.setNavigatorBooks(this)

        //отобразить список книг
            viewModel.getResults()?.observe(viewLifecycleOwner, Observer { info ->
                binding.booksRecycler.adapter = BookRecyclerViewAdapter(info.books, viewModel)
            })

        //отобразить ошибку
        viewModel.getErrorMessage().observe(viewLifecycleOwner, Observer { message ->
            showErrorMessage(message)
        })

        //установить текст выбранной из календаря даты
        binding.calendarButton?.setOnClickListener {
            val date = showCalendar()
            binding.editTextPeriod?.setText(changeDateFormat(date))
        }

        //реакция на кнопку поиска
        binding.searchButton?.setOnClickListener {
            hideKeyboard(this.context, view)
        val period: String = binding.editTextPeriod?.text.toString()
        if (period.isNotBlank())
            viewModel.setPeriod(period)
    }

        return view
    }

    /**
     * скрыть элемент при прокрутке recyclerView
     */
    private fun hideElement(view: View?, recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 10) {
                    if (view?.visibility == View.VISIBLE)
                        view.visibility = View.GONE
                } else {

//                    Log.e("scroll", "count element $r")
//                    if (view?.visibility == View.GONE)
//                        view.visibility = View.VISIBLE
                }
            }
        })
    }

    /**
     * показать календарь
     */
    private fun showCalendar(): Calendar {
        val date = Calendar.getInstance()
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            date.set(Calendar.YEAR, year)
            date.set(Calendar.MONTH, month)
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }

        DatePickerDialog(
            requireContext(), dateListener,
            date.get(Calendar.YEAR),
            date.get(Calendar.MONTH),
            date.get(Calendar.DAY_OF_MONTH)
        ).show();
        return date
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

    override fun onItemClicked(book: LiveData<BookX>?) {
        navController?.navigate(R.id.action_bookByDateFragment_to_bookFullInfoFragment)
    }

    /**
     * скрыть клавиатуру
     */
    private fun hideKeyboard(context: Context?, view: View?) {
        val imm: InputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = BookByDateFragment()
    }
}



