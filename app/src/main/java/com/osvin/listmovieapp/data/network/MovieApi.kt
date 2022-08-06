package com.osvin.listmovieapp.data.network

import com.osvin.listmovieapp.entity.Movie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    companion object{
        const val BASE_URL = "https://raw.githubusercontent.com/constanta-android-dev/intership-wellcome-task/main/"
    }

    @GET("films.json")
    fun getAllMovies(): Response<List<Movie>>
}