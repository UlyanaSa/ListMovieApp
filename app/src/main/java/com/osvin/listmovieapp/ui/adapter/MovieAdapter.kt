package com.osvin.listmovieapp.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osvin.listmovieapp.databinding.ItemMovieBinding
import com.osvin.listmovieapp.entity.NewMovie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<NewMovie>()
    var onClickItem: ((NewMovie) -> Unit)? = null

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
        holder.binding.actor.text = listMovie[position].actors
        holder.binding.directorName.text = listMovie[position].directorName
        val title = listMovie[position].title.trim(' ')
        val year = listMovie[position].releaseYear
        holder.binding.movieName.text = "$title $year"
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(listMovie[position])
        }
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }
}