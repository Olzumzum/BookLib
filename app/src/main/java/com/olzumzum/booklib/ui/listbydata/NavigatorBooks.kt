package com.olzumzum.booklib.ui.listbydata

import androidx.lifecycle.LiveData
import com.olzumzum.booklib.model.pojo.BookX

interface NavigatorBooks {
    fun onItemClicked(book: LiveData<BookX>?)
}