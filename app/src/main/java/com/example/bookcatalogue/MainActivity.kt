package com.example.bookcatalogue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookcatalogue.R.layout.activity_main
import com.example.bookcatalogue.adapter.GenreAdapter
import com.example.bookcatalogue.databinding.ActivityMainBinding
import com.example.bookcatalogue.model.Genre
import com.example.bookcatalogue.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    val GENRE = "GENRE"
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GenreAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.init(this)
        viewModel.getGenreList()?.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })



        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = GenreAdapter(viewModel.getGenreList()?.value!!, this, this::openCatalog)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun openCatalog(genre: Genre) {
        val intent = Intent(this, CatalogueActivity::class.java)
        intent.putExtra(GENRE, genre.value)
        startActivity(intent)
    }
}