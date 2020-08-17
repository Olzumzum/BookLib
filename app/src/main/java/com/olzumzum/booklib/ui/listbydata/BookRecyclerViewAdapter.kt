package com.olzumzum.booklib.ui.listbydata

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.olzumzum.booklib.R
import com.olzumzum.booklib.databinding.FragmentItemBookBinding
import com.olzumzum.booklib.model.BookX


class BookRecyclerViewAdapter : RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>() {

    private var books: List<BookX> = mutableListOf<BookX>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_book, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    fun update(value: List<BookX>) {
        books = value
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val bookImage = view.findViewById<TextView>(R.id.book_image)
            val binding = DataBindingUtil.bind<ViewDataBinding>(view)

        fun bind(book: BookX) {


        }
    }
}