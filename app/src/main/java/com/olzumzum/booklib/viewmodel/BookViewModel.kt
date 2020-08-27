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

    //для отображения одного элемента
    private lateinit var navigatorBooks: NavigatorBooks

    @Inject
    lateinit var bookRepository: BookRepository

    init {
        (application as App).getViewModelSubComponent().inject(this)
        getBooksByDate()
    }


    fun getBooksByDate(){
        infoBook = bookRepository.getInfoBook()
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



    fun itemOnClick(book: BookX) {
        Log.e("Logg", "itemOnClick")
        navigatorBooks.onItemClicked(book)
    }

    fun setNavigatorBooks(navigatorBooks: NavigatorBooks) {
        Log.e("Logg", "setNavigatorBooks")
        this@BookViewModel.navigatorBooks = navigatorBooks
    }

}