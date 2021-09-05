package com.example.bookcatalogue.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Formats (

    @Expose @SerializedName("text/html; charset=utf-8") val textHtml: String,
    @Expose@SerializedName("image/jpeg") val imageJpeg : String,
    @Expose@SerializedName("application/x-mobipocket-ebook") val application_x_mobipocket_ebook : String,
    @Expose @SerializedName("application/epub+zip") val application_epub_zip : String,
    @Expose @SerializedName("application/rdf+xml") val application_rdf_xml : String,
    @Expose @SerializedName("application/zip") val application_zip : String,
    @Expose @SerializedName("text/plain; charset=utf-8") val text_plain: String
)
