package com.raion.hunter.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.raion.hunter.R
import com.raion.hunter.landing.LandingActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProvider(this@SplashActivity)[SplashViewModel::class.java]
        val handler = Handler(mainLooper)

        handler.postDelayed({


            splashViewModel.getIsFirstTime().observe(this@SplashActivity) { isFirstTime ->
                if (isFirstTime) {
                    // Buat akun
                    /*TODO*/
                } else {

                }
            }

            val intentToMainActivity = Intent(this, LandingActivity::class.java)
            startActivity(intentToMainActivity)
            finish()
        }, 2000L)

        supportActionBar?.hide()
    }
}