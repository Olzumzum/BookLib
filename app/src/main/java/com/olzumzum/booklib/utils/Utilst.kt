package com.olzumzum.booklib.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.olzumzum.booklib.R
import com.olzumzum.booklib.model.BookX

fun LiveData<out Any>.checkDateNull(er: MutableLiveData<Int>){
    if(this.value == null)
        er.value = R.string.error_data_loading
}

