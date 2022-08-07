package com.osvin.listmovieapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.osvin.listmovieapp.R
import com.osvin.listmovieapp.databinding.FragmentDialogBinding


class MyDialogFragment : DialogFragment() {

    companion object{
        const val MOVIE_NAME = "MOVIE_NAME"
    }

    private lateinit var binding: FragmentDialogBinding
    private lateinit var nameMovie: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater, container, false)

        nameMovie = arguments?.getString(MOVIE_NAME)!!
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textDialog.text = "Movie \"" + nameMovie + "\" was clicked"
        binding.button.setOnClickListener {
            dismiss()
        }

    }



}