package com.olzumzum.booklib.ui.listbydata

import com.olzumzum.booklib.model.pojo.BookX

interface NavigatorBooks {
    fun onItemClicked(book: BookX)
}