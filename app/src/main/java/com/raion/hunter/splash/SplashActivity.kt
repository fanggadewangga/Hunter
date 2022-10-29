package com.raion.hunter.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.raion.hunter.R
import com.raion.hunter.data.UserRepository
import com.raion.hunter.dto.User
import com.raion.hunter.landing.LandingActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProvider(this@SplashActivity)[SplashViewModel::class.java]
        val handler = Handler(mainLooper)
        val userRepository = UserRepository(application)

        handler.postDelayed({


            splashViewModel.getIsFirstTime().observe(this@SplashActivity) { isFirstTime ->
                if (isFirstTime) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        userRepository.insertUser(User(null, "Gabriel", 5000))
                    }
                }
            }

            val intentToMainActivity = Intent(this, LandingActivity::class.java)
            startActivity(intentToMainActivity)
            finish()
        }, 2000L)

        supportActionBar?.hide()
    }
}