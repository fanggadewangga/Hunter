package com.raion.hunter.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raion.hunter.auth.signup.SignupActivity
import com.raion.hunter.databinding.ActivityLoginBinding
import com.raion.hunter.onboard.FirstOnboardActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            tvDaftar.setOnClickListener {
                val intentToSignup = Intent(this@LoginActivity, SignupActivity::class.java)
                startActivity(intentToSignup)
            }

            btnLogin.setOnClickListener {
                val intentToOnboard = Intent(this@LoginActivity, FirstOnboardActivity::class.java)
                startActivity(intentToOnboard)
            }
        }
    }
}