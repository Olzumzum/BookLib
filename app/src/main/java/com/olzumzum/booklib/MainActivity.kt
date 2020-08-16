package com.olzumzum.booklib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.ui.categories.CategoriesBestsellerFragment
import com.olzumzum.booklib.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Provider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.root_layout, CategoriesBestsellerFragment.newInstance())
            .commit()


    }


}