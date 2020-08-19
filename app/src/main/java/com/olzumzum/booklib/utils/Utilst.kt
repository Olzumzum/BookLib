package com.olzumzum.booklib.utils

import android.util.Log
import android.view.View
import androidx.databinding.BindingConversion
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.olzumzum.booklib.R
import com.olzumzum.booklib.model.BookX

fun LiveData<out Any>.checkDateNull(er: MutableLiveData<Int>){
    if(this.value == null)
        er.value = R.string.error_data_loading
}

@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int{
    return if (visible) View.VISIBLE else View.GONE
}

