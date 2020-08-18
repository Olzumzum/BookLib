package com.olzumzum.booklib.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.model.BookX
import com.olzumzum.booklib.model.Results
import com.olzumzum.booklib.repository.BookRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val errorMessageId: MutableLiveData<Int> = MutableLiveData()

    //сводная информация по списку бесцеллеров
    private val results: MutableLiveData<Results> = MutableLiveData()

    //список книг-бестселлеров по заданной дате
    private val books: MutableLiveData<List<BookX>> = MutableLiveData()

    private var disposable: Disposable? = null

    @Inject
    lateinit var bookRepository: BookRepository

    init {
        (application as App).getViewModelSubComponent().inject(this)
        disposable = booksByData()
    }

    /**
     * Получить информацию о списке бестселлеров
     * за указанную дату
     */
    private fun booksByData(): Disposable {
        return bookRepository.getBooksByDate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<Results>() {
                override fun onSuccess(results: Results) {
                    this@BookViewModel.results.value = results
                    books.value = results.books

                    checkDateNull(this@BookViewModel.results)
                    checkDateNull(this@BookViewModel.books)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace();
                    Log.e("MyLog", "тут ошибка")
                    errorMessageId.value = R.string.error_data_loading
                }
            })
    }

    private fun checkDateNull(livedate: LiveData<out Any>) {
        if(livedate.value == null)
                errorMessageId.value = R.string.error_data_loading
    }

    /**
     * вернуть информацию о списке бестселлеров
     * по указанной дате
     */
    fun getResults(): LiveData<Results>? {
        return results
    }

//    fun getResults(): LiveData<Results> = results

    fun getErrorMessage(): LiveData<Int> {
        return errorMessageId
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}