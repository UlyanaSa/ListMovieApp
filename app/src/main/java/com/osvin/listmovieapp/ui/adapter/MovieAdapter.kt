package com.osvin.listmovieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osvin.listmovieapp.databinding.ItemMovieBinding
import com.osvin.listmovieapp.entity.Movie
import com.osvin.listmovieapp.entity.NewMovie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<NewMovie>()
    fun setMovie(newListMovie: ArrayList<NewMovie>){
        this.listMovie = newListMovie
        notifyDataSetChanged()
    }

    class MovieViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.movieName.text = listMovie[position].title
        holder.binding.actor.text = listMovie[position].actors
        holder.binding.directorName.text = listMovie[position].directorName
        holder.binding.year.text = listMovie[position].releaseYear
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}