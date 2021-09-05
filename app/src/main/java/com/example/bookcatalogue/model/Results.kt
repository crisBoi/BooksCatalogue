package com.example.bookcatalogue.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


data class Results (

	@SerializedName("id") var id : Int,
	@SerializedName("title") var title : String,
	@SerializedName("authors") var authors : List<Authors>,
//	@SerializedName("translators") var translators : List<String>,
	@SerializedName("subjects") var subjects : List<String>,
	@SerializedName("bookshelves") var bookshelves : List<String>,
	@SerializedName("languages") var languages : List<String>,
	@SerializedName("copyright") var copyright : Boolean,
	@SerializedName("media_type") var mediaType : String,
	@SerializedName("formats") var formats : Formats,
	@SerializedName("download_count") var downloadCount : Int

)