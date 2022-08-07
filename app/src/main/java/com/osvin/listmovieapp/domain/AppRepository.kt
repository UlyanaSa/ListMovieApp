package com.osvin.listmovieapp.domain

import android.util.Log
import com.osvin.listmovieapp.data.network.MovieApi
import com.osvin.listmovieapp.entity.Actor
import com.osvin.listmovieapp.entity.Movie
import com.osvin.listmovieapp.entity.NewMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AppRepository
@Inject constructor( private val api: MovieApi) {
    companion object{
        const val TAG = "TAG"
    }

    fun transformationMovieList(): Flow<List<NewMovie>> {
        return flow<List<NewMovie>> {
            val movieList = getAllMovies()
            val newMovie = ArrayList<NewMovie>()
            for (i in movieList.indices) {
                val actors = movieList[i].actors.sortedWith(compareBy { it.actorName })
                val newActors = ArrayList<Actor>()
                for (j in 1 until actors.size) {
                    if(actors[j-1] != actors[j]){
                        newActors.add(actors[j-1])
                    }
                }
                val dir = movieList[i].directorName.split(" ")
                val dirName = dir[2]+" "+dir[0].substring(0,1)+". "+dir[1].substring(0,1)+"."
                val listActors = newActors.joinToString(separator = ", ")
                newMovie.add(
                    NewMovie(
                        actors = listActors,
                        directorName = dirName,
                        releaseYear = movieList[i].releaseYear.toString(),
                        title = movieList[i].title
                    )
                )
                emit(newMovie)
            }
        }.flowOn(Dispatchers.IO)

    }

    private suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val movieList = ArrayList<Movie>()
        val response = api.getAllMovies()
        if (response.isSuccessful) {
            if (response.body() != null) {
                val size = response.body()!!.items.size
                for (i in 0 until size) {
                    val movie = Movie(
                        directorName = response.body()!!.items[i].directorName,
                        actors = response.body()!!.items[i].actors,
                        releaseYear = response.body()!!.items[i].releaseYear,
                        title = response.body()!!.items[i].title
                    )
                    movieList.add(movie)
                }
            }
        } else {
            Log.e(TAG, "getAllMovies: ${response.message()}")
        }
        return@withContext movieList
    }


}