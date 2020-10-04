package com.olzumzum.booklib.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.model.pojo.BookX
import com.olzumzum.booklib.model.pojo.InfoWithBooks
import com.olzumzum.booklib.repository.BookRepository
import com.olzumzum.booklib.ui.listbydata.NavigatorBooks
import com.olzumzum.booklib.utils.changeDateFormat
import com.olzumzum.booklib.utils.checkDateNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class BookViewModel(application: Application) : AndroidViewModel(application) {
    //id сообщения об ошибке, если таковая имеется
    private val errorMessageId: MutableLiveData<Int> = MutableLiveData()
    //флаг состояния загрузки данных
    private var isLoaded: MutableLiveData<Boolean> = MutableLiveData()
    //сводная информация по списку бесцеллеров
    private var infoBook: LiveData<InfoWithBooks>? = null
    //список книг-бестселлеров по заданной дате
    private var books: LiveData<List<BookX>>? = null
    //книга - элемент списка бестселлеров
    var book: LiveData<BookX>? = null
    //период за который производится поиск
    private val period = MutableLiveData<String>(changeDateFormat(GregorianCalendar()))

    //для отображения одного элемента списка
    private lateinit var navigatorBooks: NavigatorBooks

    @Inject
    lateinit var bookRepository: BookRepository

    init {
        (application as App).getViewModelSubComponent().inject(this)
        getBooksByDate(period = period.value)
    }

    /**
     * получить период, за который приозводится поиск
     */
     fun getPeriod(): LiveData<String> = period

    fun setPeriod(value: String) {
        if (value.isNotBlank()) {
            period.value = value
            getBooksByDate(period.value)
        }
    }

    /**
     *вернуть информацию о списке бестселлеров по дате
     */
    private fun getBooksByDate(period: String?) {
        infoBook = bookRepository.getInfoBook(period!!)
        checkError()
    }

    /**
     * проверка на пустоту полученных списков данных
     */
    private fun checkError() = GlobalScope.launch(Dispatchers.IO){
        delay(1000)
        if(infoBook?.value?.books.isNullOrEmpty())
            errorMessageId.postValue(R.string.error_list_loading)
        infoBook?.checkDateNull(errorMessageId)

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