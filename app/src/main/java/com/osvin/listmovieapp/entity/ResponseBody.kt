package com.osvin.listmovieapp.entity


import com.google.gson.annotations.SerializedName

data class ResponseBody(
    @SerializedName("items")
    val items: List<Movie>
)