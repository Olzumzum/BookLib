package com.olzumzum.booklib.ui.book_full_info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.olzumzum.booklib.R
import com.olzumzum.booklib.app.App
import com.olzumzum.booklib.databinding.FragmentBookByDateListBinding
import com.olzumzum.booklib.databinding.FragmentBookFullInfoBinding
import com.olzumzum.booklib.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_book_full_info.*
import javax.inject.Inject

class BookFullInfoFragment : Fragment() {

    @Inject
    lateinit var viewModel: BookViewModel

    private var booksUri: String? = null
    private var firstChapterLink: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).getAppComponent().activitySubComponentBuilder()
            .with(activity = activity as AppCompatActivity)
            .build()
            .inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentBookFullInfoBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_book_full_info,
            container,
            false
        )
        //биндинг данных во вью
        val view = binding.root
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.book?.observe(viewLifecycleOwner, Observer { book ->
            booksUri = book.bookUri
            firstChapterLink = book.firstChapterLink
        })

        // реакция на нажатие кнопки показа книги по ссылке
        // открывает ссылку вида nyt://book/...
        binding.bookUriButton.setOnClickListener {
            openLink(firstChapterLink, R.string.error_link_source)
        }

        // реакция на нажатие кнопки показа первой главы книги
        // открывает ссылку вида nyt://book/...
        binding.firstChapterButton.setOnClickListener {
            openLink(firstChapterLink, R.string.error_link_source)
        }

        return view
    }

    private fun showErrorMessage(idResource: Int) {
        Snackbar.make(fragment_book_full_info, getString(idResource), Snackbar.LENGTH_LONG).show()
    }

    private fun openLink(link: String?, idErrorMessage: Int) {
        if (link != null || link != "") {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
//            startActivity(intent)
            showErrorMessage(R.string.error_api)
        } else
            showErrorMessage(idErrorMessage)
    }


}