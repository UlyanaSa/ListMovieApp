package com.osvin.listmovieapp.entity


import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("actorName")
    val actorName: String
){
    override fun toString(): String {
        return actorName
    }
}