package com.osvin.listmovieapp.entity

import com.google.gson.annotations.SerializedName

data class NewMovie(
val actors: String,
val directorName: String,
val releaseYear: String,
val title: String
)