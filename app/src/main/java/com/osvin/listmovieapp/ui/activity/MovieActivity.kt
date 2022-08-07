package com.osvin.listmovieapp.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.osvin.listmovieapp.R
import com.osvin.listmovieapp.databinding.ActivityMainBinding
import com.osvin.listmovieapp.ui.fragment.MovieFragment
import com.osvin.listmovieapp.ui.fragment.MyDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var fragment = MovieFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(getConnectionType() == 0 ){
            binding.button.isVisible = true
            binding.textInfo.isVisible = true
        }else{
            loadMovieFragment()
        }

        binding.button.setOnClickListener {
            if(getConnectionType() != 0){
                loadMovieFragment()
                binding.button.isVisible = false
                binding.textInfo.isVisible = false
            }else{
                return@setOnClickListener
            }
        }

    }

    private fun loadMovieFragment(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, fragment).commit()
    }

    private fun getConnectionType(): Int {
        var result = 0 // Returns connection type. 0: none; 1: mobile data; 2: wifi; 3: vpn
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = 2
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = 1
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_VPN)){
                        result = 3
                    }
                }
            }
        }
        return result
    }

}