package com.example.bookcatalogue.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookcatalogue.model.Genre
import com.example.bookcatalogue.repository.MainRepository

class MainActivityViewModel: ViewModel() {

    private var genreMtd: MutableLiveData<List<Genre>>? = null
    private lateinit var repository: MainRepository

    fun init(context: Context) {
        if (genreMtd != null) {
            return
        }

        repository = MainRepository.getInstance(context)
        genreMtd = repository.getMutableGenreList()
    }

    fun getGenreList(): LiveData<List<Genre>>? = genreMtd


}