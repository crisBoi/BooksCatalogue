package com.example.bookcatalogue

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookcatalogue.adapter.CatalogueAdapter
import com.example.bookcatalogue.databinding.ActivityCatalougeBinding
import com.example.bookcatalogue.model.Formats
import com.example.bookcatalogue.model.Results
import com.example.bookcatalogue.viewModel.CatalogueViewModel


class CatalogueActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCatalougeBinding
    private lateinit var viewModel: CatalogueViewModel
    private var adapter: CatalogueAdapter = CatalogueAdapter(ArrayList<Results>(), this, this::openDoc)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatalougeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(CatalogueViewModel::class.java)

        viewModel.books.observe(this, Observer {
            initRecyclerView(it, this)
        })

        viewModel.setFilterKey("")

        binding.searchEdt.addTextChangedListener { text ->
            viewModel.setFilterKey(text.toString())
        }

        initViews()

    }

    private fun initViews() {
        val genre = intent.getStringExtra("GENRE")
        binding.genreTv.text = genre!!.capitalize()
        binding.backIv.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    private fun initRecyclerView(books: List<Results>, ctx: Context) {
        adapter = CatalogueAdapter(books, this, this::openDoc)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.adapter = adapter

    }

    private fun openDoc(format: Formats) {
        var link = ""
        if (!format.text_plain.isNullOrEmpty()) {
            link = format.text_plain
        } else if (!format.textHtml.isNullOrEmpty()) {
            link = format.textHtml
        }

        if (link.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.no_available_format_found), Toast.LENGTH_LONG).show()
        } else {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(browserIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJobs()
    }
}