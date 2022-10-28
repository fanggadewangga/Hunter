package com.raion.hunter.landing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raion.hunter.auth.login.LoginActivity
import com.raion.hunter.auth.signup.SignupActivity
import com.raion.hunter.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLandingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {

            btnSignup.setOnClickListener {
                val intentToSignup = Intent(this@LandingActivity, SignupActivity::class.java)
                startActivity(intentToSignup)
            }

            btnLogin.setOnClickListener {
                val intentToLogin = Intent(this@LandingActivity, LoginActivity::class.java)
                startActivity(intentToLogin)
            }
        }
    }
}