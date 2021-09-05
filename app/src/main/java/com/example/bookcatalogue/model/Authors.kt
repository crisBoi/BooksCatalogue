package com.example.bookcatalogue.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Authors (

	@SerializedName("name") var name : String,
	@SerializedName("birth_year") var birthYear : Int,
	@SerializedName("death_year") var deathYear : Int

)