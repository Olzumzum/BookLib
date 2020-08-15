package com.olzumzum.booklib.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.olzumzum.booklib.viewmodel.BookViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    fun provideViewModule(activity: AppCompatActivity): BookViewModel{
        return ViewModelProvider(activity).get(BookViewModel::class.java)
    }
}