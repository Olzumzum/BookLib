package com.olzumzum.booklib.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.model.BookX
import com.olzumzum.booklib.model.InfoBooksByDate
import com.olzumzum.booklib.repository.BookRepository
import com.olzumzum.booklib.ui.listbydata.NavigatorBooks
import com.olzumzum.booklib.utils.checkDateNull
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val errorMessageId: MutableLiveData<Int> = MutableLiveData()
    private var isLoaded: MutableLiveData<Boolean> = MutableLiveData()

    //сводная информация по списку бесцеллеров
    private var infoBooksByDate: LiveData<InfoBooksByDate>? = null
    //список книг-бестселлеров по заданной дате
    private val books: MutableLiveData<List<BookX>> = MutableLiveData()

    //для отображения одного элемента
    private lateinit var navigatorBooks: NavigatorBooks

    @Inject
    lateinit var bookRepository: BookRepository

    init {
        (application as App).getViewModelSubComponent().inject(this)
        getBooksByDate()
    }


    fun getBooksByDate(){
        infoBooksByDate = bookRepository.getBooksByDate()
        infoBooksByDate!!.checkDateNull(errorMessageId)
    }
    /**
     * вернуть информацию о списке бестселлеров
     * по указанной дате
     */
    fun getResults(): LiveData<InfoBooksByDate>? = infoBooksByDate


    fun getBooks(): LiveData<List<BookX>> = books

    fun getErrorMessage(): LiveData<Int> {
        return errorMessageId
    }

    fun getIsLoaded(): LiveData<Boolean> = isLoaded



    fun itemOnClick(book: BookX) {
        Log.e("Logg", "itemOnClick")
        navigatorBooks.onItemClicked(book)
    }

    fun setNavigatorBooks(navigatorBooks: NavigatorBooks) {
        Log.e("Logg", "setNavigatorBooks")
        this@BookViewModel.navigatorBooks = navigatorBooks
    }

}