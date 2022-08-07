package com.osvin.listmovieapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.osvin.listmovieapp.R
import com.osvin.listmovieapp.databinding.ActivityMainBinding
import com.osvin.listmovieapp.databinding.FragmentMovieBinding
import com.osvin.listmovieapp.ui.fragment.MovieFragment
import com.osvin.listmovieapp.ui.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var fragment = MovieFragment()
    var saveState: Boolean = false
    companion object {
        const val FRAGMENT_STATE = "FRAGMENT_STATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // if(!saveState){
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_container, fragment).commit()
       //     saveState = true
      //  }

    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putBoolean(FRAGMENT_STATE, saveState)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        saveState = savedInstanceState.getBoolean(FRAGMENT_STATE)
//    }

}