package com.example.bookcatalogue.api

import com.example.bookcatalogue.model.Data
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiService {

    @GET("books/")
    suspend fun getBooks(): Data
}