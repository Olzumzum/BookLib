package com.olzumzum.booklib.di.component

import com.olzumzum.booklib.MainActivity
import com.olzumzum.booklib.di.module.ApiModule
import com.olzumzum.booklib.di.module.BookRepositoryModule
import com.olzumzum.booklib.viewmodel.BookViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, BookRepositoryModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(viewModel: BookViewModel)
}