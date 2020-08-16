package com.olzumzum.booklib.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.model.Category
import com.olzumzum.booklib.repository.BookRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class BookViewModel(application: Application): AndroidViewModel(application) {
    private val allCategoriesLiveData: MutableLiveData<List<Category>> by lazy {
        MutableLiveData<List<Category>>()
    }
    private val errorMessageId: MutableLiveData<Int> = MutableLiveData()
    private var disposable: Disposable? = null

    @Inject
    lateinit var bookRepository: BookRepository


    init {
        (application as App).getViewModelSubComponent().inject(this)

        disposable = allBook()
    }

    /**
     * Список категорий бестселлеров
     */
    private fun allBook(): Disposable {
       return bookRepository.getAllBook()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<Category>>(){
                override fun onSuccess(categories: List<Category>) {
                    allCategoriesLiveData.value = categories
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace();
                    Log.e("MyLog", "тут ошибка")
                    errorMessageId.value = R.string.error_data_loading
                }

            })
    }

    private fun booksByData(): Disposable{
       return bookRepository.getBooksByDate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<Int>(){
                override fun onSuccess(books: Int) {
//                    allBookLiveData.value = books.toString()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace();
                    Log.e("MyLog", "тут ошибка")
                    errorMessageId.value = R.string.error_data_loading
                }

            })
    }



    fun getAllBook(): MutableLiveData<List<Category>> {
        return allCategoriesLiveData
    }

    fun unsubscribe(){
        disposable?.dispose()
    }

    fun getErrorMessage(): LiveData<Int>{
        return errorMessageId
    }
}