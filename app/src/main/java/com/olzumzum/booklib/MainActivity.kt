package com.olzumzum.booklib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.olzumzum.booklib.ui.listbydata.BookByDateFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.root_layout, BookByDateFragment.newInstance(1))
            .commit()


    }


}