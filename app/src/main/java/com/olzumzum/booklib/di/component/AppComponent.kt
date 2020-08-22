package com.olzumzum.booklib.di.component

import android.app.Application
import com.olzumzum.booklib.di.module.ApiModule
import com.olzumzum.booklib.di.module.BookRepositoryModule
import com.olzumzum.booklib.di.module.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, BookRepositoryModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun withApplication(application: Application): Builder
        fun build(): AppComponent
    }

    fun viewModelSubComponentBuilder(): ViewModuleSubComponent.Builder
    fun activitySubComponentBuilder(): ActivitySubComponent.Builder
}