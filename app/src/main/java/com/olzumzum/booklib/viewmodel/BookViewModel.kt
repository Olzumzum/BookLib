package com.olzumzum.booklib.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.model.pojo.BookX
import com.olzumzum.booklib.model.pojo.InfoWithBooks
import com.olzumzum.booklib.repository.BookRepository
import com.olzumzum.booklib.ui.listbydata.NavigatorBooks
import com.olzumzum.booklib.utils.checkDateNull
import javax.inject.Inject

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val errorMessageId: MutableLiveData<Int> = MutableLiveData()
    private var isLoaded: MutableLiveData<Boolean> = MutableLiveData()

    //сводная информация по списку бесцеллеров
    private var infoBook: LiveData<InfoWithBooks>? = null

    //список книг-бестселлеров по заданной дате
    private var books: LiveData<List<BookX>>? = null
    var book: LiveData<BookX>? = null
    private var period: String = ""

    //для отображения одного элемента
    private lateinit var navigatorBooks: NavigatorBooks

    @Inject
    lateinit var bookRepository: BookRepository

    init {
        (application as App).getViewModelSubComponent().inject(this)
        getBooksByDate(getPeriod())
    }

    /**
     * получить период, за который приозводится поиск
     */
    private fun getPeriod(): String{
        return if (period == "")
            "2020-08-01"
        else period
    }

    private fun getBooksByDate(period: String) {
        infoBook = bookRepository.getInfoBook(period)
//        infoBook!!.checkDateNull(errorMessageId)
        books = bookRepository.getBooks()
//        books?.checkDateNull(errorMessageId)
    }

    /**
     * вернуть информацию о списке бестселлеров
     * по указанной дате
     */
    fun getResults(): LiveData<InfoWithBooks>? = infoBook


    fun getBooks(): LiveData<List<BookX>>? = books

    fun getErrorMessage(): LiveData<Int> {
        return errorMessageId
    }

    fun getIsLoaded(): LiveData<Boolean> = isLoaded


    fun itemOnClick(_book: BookX) {
        Log.e("Logg", "itemOnClick ${_book.id}")
        book = bookRepository.getBookByData(_book.id)
        navigatorBooks.onItemClicked(book)
    }

    fun setNavigatorBooks(navigatorBooks: NavigatorBooks) {
        Log.e("Logg", "setNavigatorBooks")
        this@BookViewModel.navigatorBooks = navigatorBooks
    }

    override fun onCleared() {
        super.onCleared()
        bookRepository.clear()
    }

}