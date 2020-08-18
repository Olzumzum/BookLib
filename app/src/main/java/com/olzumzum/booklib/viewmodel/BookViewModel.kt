package com.olzumzum.booklib.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.model.BookX
import com.olzumzum.booklib.model.InfoByDateBook
import com.olzumzum.booklib.repository.BookRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookViewModel(application: Application): AndroidViewModel(application) {
    private val infoByDateBookLiveData: MutableLiveData<InfoByDateBook> by lazy {
        MutableLiveData<InfoByDateBook>()
    }
    private val books: MutableLiveData<List<BookX>> by lazy {
        MutableLiveData<List<BookX>>()
    }

    private val titile: String
    private val author: String
    private val bookImageUrl: String
    private val rank: String


    @Inject
    lateinit var bookRepository: BookRepository

    private val errorMessageId: MutableLiveData<Int> = MutableLiveData()
    private var disposable: Disposable? = null

    init {
        (application as App).getViewModelSubComponent().inject(this)

        disposable = booksByData()
    }


    private fun booksByData(): Disposable {

        return bookRepository.getBooksByDate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<InfoByDateBook>() {
                override fun onSuccess(infoByDateBook: InfoByDateBook) {
                    infoByDateBookLiveData.value = infoByDateBook
                    books.value = infoByDateBook.books
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace();
                    Log.e("MyLog", "тут ошибка")
                    errorMessageId.value = R.string.error_data_loading
                }

            })
    }


    fun getResults(): LiveData<InfoByDateBook> {
        disposable = booksByData()
        return infoByDateBookLiveData

    }

    fun getBooks(): LiveData<List<BookX>> = books

    fun getErrorMessage(): LiveData<Int> {
        return errorMessageId
    }
}