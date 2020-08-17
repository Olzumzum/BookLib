package com.olzumzum.booklib.ui.listbydata

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.olzumzum.booklib.R
import com.olzumzum.booklib.model.BookX
import com.olzumzum.booklib.model.Results
import org.w3c.dom.Text


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
        val title = view.findViewById<TextView>(R.id.title)
        val author = view.findViewById<TextView>(R.id.author)
        val bookImage = view.findViewById<TextView>(R.id.book_image)
        val rank = view.findViewById<TextView>(R.id.rank)
        val rankLastWeek = view.findViewById<TextView>(R.id.rank_last_week)
        val weeksOnList = view.findViewById<TextView>(R.id.weeks_on_list)

        fun bind(book: BookX) {
            title.text = book.title
            author.text = book.author
            bookImage.text = book.book_image
            rank.text = "${book.rank}"
            rankLastWeek.text = "${book.rank_last_week}"
            weeksOnList.text = "${book.weeks_on_list}"

        }
    }
}