package com.example.bookcatalogue.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.bookcatalogue.model.Genre

class MainRepository {

    companion object{
        private var repository: MainRepository? = null
        private lateinit var genreList: List<Genre>
        private lateinit var context: Context

        fun getInstance(context: Context): MainRepository {
            if (repository == null) {
                repository = MainRepository()
                this.context = context
            }
            return repository!!
        }
    }

    public fun getMutableGenreList ():MutableLiveData<List<Genre>> {
        genreList = Genre.generateGenreList()

        val mutableLiveData = MutableLiveData<List<Genre>>()
        mutableLiveData.value = genreList
        return mutableLiveData
    }



}