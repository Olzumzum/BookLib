package com.olzumzum.booklib.ui.listbydata

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.olzumzum.booklib.R
import com.olzumzum.booklib.model.Results

import com.olzumzum.booklib.ui.listbydata.dummy.DummyContent.DummyItem
import java.nio.file.Files.size


class MyBookRecyclerViewAdapter: RecyclerView.Adapter<MyBookRecyclerViewAdapter.ViewHolder>() {

    private var results: List<Results> = mutableListOf<Results>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_book_by_date, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int = results.size

    fun update(value: List<Results>){
        results = value
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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
        val published_date_description: TextView = view.findViewById(R.id.published_date_description)
        val updated: TextView = view.findViewById(R.id.updated)

       fun bind(results: Results){
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

       }
    }
}