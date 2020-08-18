package com.olzumzum.booklib.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.olzumzum.booklib.viewmodel.BookViewModel
import com.olzumzum.booklib.viewmodel.CategoriesViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    fun provideCategoriesViewModule(activity: AppCompatActivity): CategoriesViewModel{
        return ViewModelProvider(activity).get(CategoriesViewModel::class.java)
    }

    @Provides
    fun provideBookViewModule(activity: AppCompatActivity): BookViewModel{
        return ViewModelProvider(activity).get(BookViewModel::class.java)
    }
}