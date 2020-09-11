package com.olzumzum.booklib.ui.listbydata

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
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
import java.net.DatagramPacket
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class BookByDateFragment : Fragment(), NavigatorBooks {

    private val DATE_FORMAT = "yyyy-MM-dd"

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
        val recyclerView = view.findViewById<RecyclerView>(R.id.books_by_date_lsit)
        recyclerView.addItemDecoration(RecyclerDivider(requireContext()))
        binding.booksByDateLsit.layoutManager = LinearLayoutManager(context)

        //обработка нажатия на элемент списка
        viewModel.setNavigatorBooks(this)

        //отобразить список книг
        if (savedInstanceState == null)
            viewModel.getBooks()?.observe(viewLifecycleOwner, Observer { books ->
                binding.booksByDateLsit.adapter = BookRecyclerViewAdapter(books, viewModel)
            })

        //отобразить ошибку
        viewModel.getErrorMessage().observe(viewLifecycleOwner, Observer { message ->
            showErrorMessage(message)
        })

        //установить текст выбранной из календаря даты
        binding.calendarButton?.setOnClickListener {
            val date = showCalendar()
            binding.editTextPeriod?.setText(updateLabel(date))
        }

        binding.searchButton?.setOnClickListener {
            hideKeyboard(this.context, view)
            val period:String = binding.editTextPeriod?.text.toString()
            if (period.isNotBlank())
                viewModel.setPeriod(period)
        }


        return view
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

    /**
     * привести дату к нужному виду
     * на вход получаем необходимую дату
     * выход - ее строковое представление
     */
    private fun updateLabel(date: Calendar): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.US)
        return sdf.format(date.time)
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
    private fun hideKeyboard(context: Context?, view: View?){
        val imm: InputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = BookByDateFragment()
    }
}



