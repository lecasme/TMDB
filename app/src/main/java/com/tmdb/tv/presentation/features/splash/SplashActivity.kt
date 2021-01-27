package com.tmdb.tv.presentation.features.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tmdb.tv.databinding.ActivitySplashBinding
import com.tmdb.tv.presentation.features.home.HomeActivity
import org.koin.android.viewmodel.ext.android.viewModel


class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.status.observe(this, {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        })

    }



}