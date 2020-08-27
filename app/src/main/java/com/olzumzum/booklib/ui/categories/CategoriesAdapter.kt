package com.olzumzum.booklib.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olzumzum.booklib.R
import com.olzumzum.booklib.model.dto.Category
import kotlinx.android.synthetic.main.item_category_bestseller.view.*

class CategoriesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categories = mutableListOf<Category>()

    fun updateData(data: List<Category>){
        categories = data as MutableList<Category>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_bestseller, parent, false)
        return CategoriesHolder(view)
    }

    override fun getItemCount(): Int = categories.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoriesHolder).bind(category = categories[position])
    }

    class CategoriesHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(category: Category) = with(itemView){
            display_name.text = category.display_name
            newest_published_date.text = category.newest_published_date
            oldest_published_date.text = category.oldest_published_date
            updated.text = category.updated
        }
    }
}