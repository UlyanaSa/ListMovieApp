package com.osvin.listmovieapp.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.osvin.listmovieapp.databinding.ItemMovieBinding
import com.osvin.listmovieapp.domain.MovieDiffUtilCallback

import com.osvin.listmovieapp.entity.NewMovie
import com.osvin.listmovieapp.ui.fragment.MyDialogFragment

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<NewMovie>()
    var onClickItem: ((NewMovie) -> Unit)? = null

    fun setMovie(newListMovie: ArrayList<NewMovie>){
        val diffUtilResult = DiffUtil.calculateDiff(MovieDiffUtilCallback(listMovie, newListMovie))
        listMovie = newListMovie
        diffUtilResult.dispatchUpdatesTo(this)
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
        holder.binding.movieName.text = listMovie[position].title.trim(' ')+", "+listMovie[position].releaseYear
        holder.binding.actor.text = listMovie[position].actors
        holder.binding.directorName.text = listMovie[position].directorName

        holder.itemView.setOnClickListener {
            onClickItem?.invoke(listMovie[position])
        }
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}