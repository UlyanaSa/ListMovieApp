package com.osvin.listmovieapp.domain

import android.util.Log
import com.osvin.listmovieapp.data.network.MovieApi
import com.osvin.listmovieapp.entity.Actor
import com.osvin.listmovieapp.entity.Movie
import com.osvin.listmovieapp.entity.NewMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(private val api: MovieApi) {

    companion object{
        const val TAG = "TAG"
    }

    private fun transformationMovieList(movieList: List<Movie>): List<NewMovie> {
        val newMovie = ArrayList<NewMovie>()
        for (i in movieList.indices) {
            val actors = movieList[i].actors.sortedWith(compareBy { it.actorName })
            val newActors = ArrayList<Actor>()
            for (j in actors.indices) {
               if(actors[j] != actors[j+1]){
                   newActors.add(actors[j])
               }
            }
            val dir = movieList[i].directorName.trim(' ').toList()
            val dirName = dir[2].toString()+" "+dir[0].toString().substring(0,1)+"."+dir[1].toString().substring(0,1)+"."
            val listActors = newActors.joinToString(separator = ", ")
            newMovie.add(
                NewMovie(
                    actors = listActors,
                    directorName = dirName,
                    releaseYear = movieList[i].releaseYear,
                    title = movieList[i].title
                )
            )
        }
        return newMovie
    }

    private suspend fun getAllMovies():List<Movie> = withContext(Dispatchers.IO) {
        val movieList = ArrayList<Movie>()
        val response = api.getAllMovies()
        if(response.isSuccessful){
            if(response.body() != null ){
                val size = response.body()!!.size
                for(i in 0 until size){
                    val movie = Movie(
                        directorName = response.body()!![i].directorName,
                        actors = response.body()!![i].actors,
                        releaseYear = response.body()!![i].releaseYear,
                        title = response.body()!![i].title
                    )
                    movieList.add(movie)
                }
            }
        }else {
            Log.e(TAG, "getAllMovies: ${response.message()}")
        }
        return@withContext movieList
    }


}