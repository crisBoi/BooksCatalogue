package com.example.bookcatalogue.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.bookcatalogue.model.Results
import com.example.bookcatalogue.repository.CatalogueRepository

class CatalogueViewModel: ViewModel() {

    private val filterKey: MutableLiveData<String> = MutableLiveData()


    val books: LiveData<List<Results>> = Transformations
            .switchMap(filterKey) { searchTerm ->
                val allBooks = CatalogueRepository.getBooks()
                val searchedBooks = when {
                    searchTerm.isNullOrEmpty() -> allBooks
                    else -> {
                        Transformations.switchMap(allBooks) {bookList ->
                            val filteredMutableData = MutableLiveData<List<Results>>()
                            val filtererList = bookList.filter { book ->
                                ((!book.authors[0].name.isNullOrEmpty() && book.authors[0].name.toLowerCase().startsWith(searchTerm.toLowerCase()))
                                        ||
                                        (!book.title.isNullOrEmpty() && book.title.toLowerCase().startsWith(searchTerm.toLowerCase())))

//                                true
                            }

                            filteredMutableData.value = filtererList
                            filteredMutableData
                        }
                    }

                }
                searchedBooks
            }

    fun setFilterKey(key: String) {
        val update = key
        if (filterKey.value == update) {
            return
        }
        filterKey.value = update
    }



    fun cancelJobs() {
        CatalogueRepository.cancelJob()
    }
}