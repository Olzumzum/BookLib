package com.olzumzum.booklib.di.component

import androidx.appcompat.app.AppCompatActivity
import com.olzumzum.booklib.MainActivity
import com.olzumzum.booklib.di.module.ActivityModule
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
}