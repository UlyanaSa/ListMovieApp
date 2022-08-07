package com.osvin.listmovieapp.ui.fragment
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.osvin.listmovieapp.databinding.FragmentMovieBinding
import com.osvin.listmovieapp.entity.NewMovie
import com.osvin.listmovieapp.ui.adapter.MovieAdapter
import com.osvin.listmovieapp.ui.viewModel.MovieViewModel

class MovieFragment : Fragment() {
    companion object{
        const val MOVIE_NAME = "MOVIE_NAME"
        const val REQUEST_KEY = "REQUEST_KEY"
    }
    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private val movieViewModel: MovieViewModel by activityViewModels()
    private lateinit var nameMovie: String
    private lateinit var saveMovieList: List<NewMovie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = movieAdapter
        }

        movieViewModel.getAllMovies()

        observeMovieList()
        observePosition()
        observeCheckedMovie()

        movieAdapter.onClickItem = {
            movieViewModel.isCheckedMovie(it)
            createdDialogFragment(nameMovie)
        }
    }

    override fun onStop() {
        super.onStop()
        val newPosition = (binding.recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
        movieViewModel.savePosition(newPosition)
       // запускаем сохраненный лист фильмов. такое легкое сохранение рассчитано только на то,
        // что лист фильмов уже был когда-то загружен
        movieViewModel.saveData(saveMovieList)
    }

    private fun createdDialogFragment(title: String){

        val dialogFragment = MyDialogFragment()
        val bundle = Bundle()
        bundle.putString(MOVIE_NAME, title)
        dialogFragment.arguments = bundle
        dialogFragment.show(childFragmentManager, REQUEST_KEY)
    }

    private fun observeCheckedMovie() {
        movieViewModel.titleMovieLiveData.observe(viewLifecycleOwner, Observer {
            nameMovie = it
        })
    }

    private fun observePosition() {
        movieViewModel.positionLiveData.observe(viewLifecycleOwner, Observer {
            (binding.recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(it)
        })
    }

    private fun observeMovieList() {
        movieViewModel.movieListLiveData.observe(viewLifecycleOwner, Observer {
          // сохраняем лист на случай отключения интернет соединения
            saveMovieList = it
            movieAdapter.setMovie(it as ArrayList<NewMovie>)
        })
    }




}