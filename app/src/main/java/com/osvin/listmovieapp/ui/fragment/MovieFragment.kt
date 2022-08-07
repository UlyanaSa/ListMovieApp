package com.osvin.listmovieapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.osvin.listmovieapp.R
import com.osvin.listmovieapp.data.network.MovieApi
import com.osvin.listmovieapp.data.network.RetrofitInstance
import com.osvin.listmovieapp.databinding.FragmentMovieBinding
import com.osvin.listmovieapp.domain.AppRepository
import com.osvin.listmovieapp.entity.NewMovie
import com.osvin.listmovieapp.ui.adapter.MovieAdapter
import com.osvin.listmovieapp.ui.viewModel.MovieViewModel
import com.osvin.listmovieapp.ui.viewModel.MovieViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieApi = RetrofitInstance.api
        val appRepository = AppRepository(movieApi)
        movieViewModel = ViewModelProvider(this,MovieViewModelFactory(appRepository))[MovieViewModel::class.java]
        movieAdapter = MovieAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = movieAdapter
        }


        observeMovieVM()
        movieViewModel.getAllMovies()
        movieAdapter.onClickItem
    }

    private fun observeMovieVM() {
        movieViewModel.movieListLiveData.observe(viewLifecycleOwner, Observer {
            movieAdapter.setMovie(it as ArrayList<NewMovie>)
        })
    }

}