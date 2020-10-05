package com.olzumzum.booklib.ui.listbydata

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olzumzum.booklib.databinding.FragmentItemBookBinding
import com.olzumzum.booklib.model.pojo.BookX
import com.olzumzum.booklib.viewmodel.BookViewModel


class BookRecyclerViewAdapter(
    var viewModel: BookViewModel
) : RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>() {

    private var books: List<BookX>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)

        val binding = FragmentItemBookBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding, binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books?.get(position)
        holder.setBinding(book, viewModel)
    }

    override fun getItemCount(): Int {
        if (books.isNullOrEmpty())
            return 0

        if (books?.size == 0)
            return 0

        return books?.size!!
    }

    fun fillBooks(_books: List<BookX>?) {
        books = _books
        notifyDataSetChanged()
        Log.e("scroll", "Пришедшие данные ${_books?.get(0)?.title}")
        Log.e("scroll", "Данные изменены ${books?.get(0)?.title}")
    }


    inner class ViewHolder(private val binding: FragmentItemBookBinding, view: View) :
        RecyclerView.ViewHolder(view) {
        fun setBinding(book: BookX?, viewModel: BookViewModel) {
            binding.book = book
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

    }
}
