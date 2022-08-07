package com.osvin.listmovieapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.osvin.listmovieapp.R
import com.osvin.listmovieapp.data.network.RetrofitInstance
import com.osvin.listmovieapp.databinding.FragmentDialogBinding
import com.osvin.listmovieapp.domain.AppRepository
import com.osvin.listmovieapp.ui.viewModel.MovieViewModel


class MyDialogFragment: DialogFragment() {

    companion object{
        const val MOVIE_NAME = "MOVIE_NAME"
    }
  //  private lateinit var sharedMovieViewModel: MovieViewModel
    private lateinit var binding: FragmentDialogBinding
    private lateinit var nameMovie: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater, container, false)

        //sharedMovieViewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]


        nameMovie = arguments?.getString(MOVIE_NAME)!!
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //sharedMovieViewModel.checkedMovieLiveData.observe(viewLifecycleOwner, Observer {
         //   nameMovie = it
        //})
        binding.textDialog.text = "Movie \"" + nameMovie + "\" was clicked"
        binding.button.setOnClickListener {
            dismiss()
        }

    }



}