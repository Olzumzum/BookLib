package com.olzumzum.booklib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.olzumzum.booklib.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Provider

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        viewModel.getAllBook().observe(this, Observer{
            tv.setText(it)
        })

        viewModel.getErrorMessage().observe(this, Observer {message ->
            showErrorMessage(message)
        })
    }

    fun showErrorMessage(idResource: Int){
        Snackbar.make(root_layout, getString(idResource), Snackbar.LENGTH_LONG).show()
    }
}