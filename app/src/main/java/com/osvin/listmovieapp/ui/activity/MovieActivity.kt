package com.osvin.listmovieapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.osvin.listmovieapp.R
import com.osvin.listmovieapp.databinding.ActivityMainBinding
import com.osvin.listmovieapp.ui.fragment.MovieFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var fragment = MovieFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, fragment).commit()

    }
}