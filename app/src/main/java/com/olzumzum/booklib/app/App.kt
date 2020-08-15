package com.olzumzum.booklib.app

import android.app.Application
import com.olzumzum.booklib.di.component.AppComponent
import com.olzumzum.booklib.di.component.DaggerAppComponent
import com.olzumzum.booklib.repository.BookRepository

class App: Application() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    fun initDagger(){
        appComponent = DaggerAppComponent.create()

    }

    fun getAppComponent(): AppComponent = appComponent!!
}