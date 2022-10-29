package com.raion.hunter.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.raion.hunter.R
import com.raion.hunter.landing.LandingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(mainLooper)

        handler.postDelayed({
            val intentToMainActivity = Intent(this, LandingActivity::class.java)
            startActivity(intentToMainActivity)
            finish()
        }, 2000L)

        supportActionBar?.hide()
    }
}