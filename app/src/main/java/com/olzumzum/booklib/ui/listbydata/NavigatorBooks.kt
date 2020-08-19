package com.olzumzum.booklib.ui.listbydata

import com.olzumzum.booklib.model.BookX

interface NavigatorBooks {
    fun onItemClicked(book: BookX)
}