package com.olzumzum.booklib.ui.listbydata

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.olzumzum.booklib.R
import com.olzumzum.booklib.databinding.FragmentItemBookBinding
import com.olzumzum.booklib.model.BookX
import com.olzumzum.booklib.model.Results
import org.w3c.dom.Text


class BookRecyclerViewAdapter : RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>() {

    private var books: List<BookX> = mutableListOf<BookX>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)

        val binding = FragmentItemBookBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.binding?.book = book
    }

    override fun getItemCount(): Int = books.size

    fun update(value: List<BookX>) {
        books = value
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: FragmentItemBookBinding? = DataBindingUtil.bind<FragmentItemBookBinding>(view)


    }
}