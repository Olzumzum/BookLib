package com.olzumzum.booklib.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olzumzum.booklib.R
import com.olzumzum.booklib.repository.BookRepository
import com.olzumzum.bookslibrary.model.Book
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class BookViewModel(): ViewModel() {
    private val allBookLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val errorMessageId: MutableLiveData<Int> = MutableLiveData()

    private var disposable: Disposable? = null

    private val repository: BookRepository = BookRepository()

    init {
        disposable = repository.getAllBook()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<Book>(){
                override fun onSuccess(book: Book) {
                    allBookLiveData.value = book.title
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace();
                    Log.e("MyLog", "тут ошибка")
                    errorMessageId.value = R.string.error_data_loading
                }

            })
    }

    fun getAllBook(): LiveData<String>{
        return allBookLiveData
    }

    fun unsubscribe(){
        disposable?.dispose()
    }

    fun getErrorMessage(): LiveData<Int>{
        return errorMessageId
    }
}