package com.example.bookcatalogue.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data (

	@SerializedName("count") var count : Int,
	@SerializedName("next") var next : String,
	@SerializedName("previous") var previous : String,
	@SerializedName("results") var results : List<Results>

)