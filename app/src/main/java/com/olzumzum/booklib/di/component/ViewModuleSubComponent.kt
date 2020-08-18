package com.olzumzum.booklib.di.component

import com.olzumzum.booklib.viewmodel.BookViewModel
import com.olzumzum.booklib.viewmodel.CategoriesViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModuleSubComponent {
    @Subcomponent.Builder
    interface Builder{
        fun build(): ViewModuleSubComponent
    }

    fun inject(viewModel: CategoriesViewModel)
    fun inject(viewModel: BookViewModel)
}