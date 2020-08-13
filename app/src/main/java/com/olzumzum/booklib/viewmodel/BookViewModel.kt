package com.olzumzum.booklib.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olzumzum.booklib.repository.BookRepository
import com.olzumzum.bookslibrary.model.Book
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class BookViewModel(): ViewModel() {
    private val allBookLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    var diposable: Disposable? = null

    private val repository: BookRepository = BookRepository()

    init {
        diposable = repository.getAllBook()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<Book>(){
                override fun onSuccess(book: Book) {
                    allBookLiveData.value = book.title
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace();
                }

            })


    }

    fun getAllBook(): LiveData<String>{
        return allBookLiveData
    }

    fun unsubscribe(){

    }
}