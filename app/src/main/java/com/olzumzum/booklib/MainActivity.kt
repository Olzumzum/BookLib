package com.olzumzum.booklib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.olzumzum.booklib.ui.listbydata.BookByDateFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R
                .id.home) {
            super.onBackPressed()
            supportActionBar?.setDisplayShowHomeEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        return super.onOptionsItemSelected(item)
    }

}