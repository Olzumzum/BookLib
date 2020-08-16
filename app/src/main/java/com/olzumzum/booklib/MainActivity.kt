package com.olzumzum.booklib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Provider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getAppComponent().activitySubComponentBuilder()
            .with(this)
            .build()
            .inject(this)

        viewModel.getAllBook().observe(this, Observer{book ->
            display_name.text = book.display_name
            list_name.text = book.list_name
            list_name_encoded.text = book.list_name_encoded
            newest_published_date.text = book.newest_published_date
            oldest_published_date.text = book.oldest_published_date
            updated.text = book.updated
        })

        viewModel.getErrorMessage().observe(this, Observer {message ->
            showErrorMessage(message)
        })
    }

    fun showErrorMessage(idResource: Int){
        Snackbar.make(root_layout, getString(idResource), Snackbar.LENGTH_LONG).show()
    }
}