package com.raion.hunter.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raion.hunter.R
import com.raion.hunter.auth.signup.SignupActivity
import com.raion.hunter.databinding.ActivityLandingBinding
import com.raion.hunter.databinding.ActivityLoginBinding
import com.raion.hunter.databinding.ActivitySignupBinding

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
        }
    }
}