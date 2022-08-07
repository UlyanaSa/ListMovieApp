package com.osvin.listmovieapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.osvin.listmovieapp.R
import com.osvin.listmovieapp.ui.fragment.MovieFragment

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, MovieFragment()).commit()

    }



}