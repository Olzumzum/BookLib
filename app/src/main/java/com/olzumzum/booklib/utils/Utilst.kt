package com.olzumzum.booklib.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.olzumzum.booklib.R
import com.squareup.picasso.Picasso

fun LiveData<out Any>.checkDateNull(er: MutableLiveData<Int>){
    if(this.value == null)
        er.value = R.string.error_data_loading
}

/**
 * конвертация значений для отображения view
 * (progressBar)
 */
@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int{
    return if (visible) View.VISIBLE else View.GONE
}

/**
 * Загрузка изображений
 */
@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?){
    Picasso.get()
        .load(url)
        .placeholder(R.color.colorPrimaryDark)
        .error(R.color.colorAccent)
        .fit()
        .into(imageView)
}
