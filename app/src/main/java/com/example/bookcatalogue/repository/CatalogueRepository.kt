package com.example.bookcatalogue.repository

import androidx.lifecycle.LiveData
import com.example.bookcatalogue.api.RetrofitBuilder
import com.example.bookcatalogue.model.Results
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object CatalogueRepository {

    var job: CompletableJob? = null

    fun getBooks(): LiveData<List<Results>> {

        job = Job()

        return object : LiveData<List<Results>>() {
            override fun onActive() {
                super.onActive()

                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val data = RetrofitBuilder.apiService.getBooks()
                        withContext(Main) {
                            value = data.results
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJob() {
        job?.cancel()
    }

}