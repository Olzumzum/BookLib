package com.olzumzum.booklib.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olzumzum.booklib.repository.BookRepository


class BookViewModel(): ViewModel() {
    private val allBookLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val repository: BookRepository = BookRepository()
    init {
        repository.getAllBook()?.doOnSuccess{
            allBookLiveData.value = ""

        }
            ?.doOnError{
                allBookLiveData.value = ""
            }
    }

    fun getAllBook(): LiveData<String>{
        return allBookLiveData
    }
}