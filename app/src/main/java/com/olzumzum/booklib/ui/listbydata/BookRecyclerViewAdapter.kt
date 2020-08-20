package com.olzumzum.booklib.ui.listbydata

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.olzumzum.booklib.databinding.FragmentItemBookBinding
import com.olzumzum.booklib.model.BookX
import com.olzumzum.booklib.viewmodel.BookViewModel


class BookRecyclerViewAdapter(
    var books: List<BookX>,
    var viewModel: BookViewModel
) : RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>() {


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
        val book = books[position]
        holder.setBinding(book, viewModel)
    }

    override fun getItemCount(): Int = books.size




    inner class ViewHolder(val binding: FragmentItemBookBinding, view: View) :
        RecyclerView.ViewHolder(view) {
        fun setBinding(book: BookX, viewModel: BookViewModel) {
            binding.book = book
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

    }
}