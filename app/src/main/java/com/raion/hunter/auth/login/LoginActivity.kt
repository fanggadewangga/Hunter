package com.raion.hunter.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raion.hunter.auth.signup.SignupActivity
import com.raion.hunter.databinding.ActivityLoginBinding
import com.raion.hunter.onboard.FirstOnboardActivity
import com.raion.hunter.onboard.SecondOnboardActivity

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
                finish()
            }

            btnLogin.setOnClickListener {
                val intentToOnboard = Intent(this@LoginActivity, SecondOnboardActivity::class.java)
                startActivity(intentToOnboard)
                finish()
            }
        }
    }
}