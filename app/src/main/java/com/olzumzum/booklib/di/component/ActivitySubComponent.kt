package com.olzumzum.booklib.di.component

import androidx.appcompat.app.AppCompatActivity
import com.olzumzum.booklib.MainActivity
import com.olzumzum.booklib.di.module.ActivityModule
import com.olzumzum.booklib.ui.book_full_info.BookFullInfoFragment
import com.olzumzum.booklib.ui.categories.CategoriesBestsellerFragment
import com.olzumzum.booklib.ui.listbydata.BookByDateFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
interface ActivitySubComponent {
    @Subcomponent.Builder
    interface Builder{
        @BindsInstance
        fun with(activity: AppCompatActivity):Builder
        fun build(): ActivitySubComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(categoriesBestsellerFragment: CategoriesBestsellerFragment)
    fun inject(bookByDateFragment: BookByDateFragment)
    fun inject(bookFullInfoFragment: BookFullInfoFragment)
}