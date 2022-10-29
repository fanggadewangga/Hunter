package com.raion.hunter.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raion.hunter.R
import com.raion.hunter.auth.login.LoginActivity
import com.raion.hunter.camera.CameraActivity
import com.raion.hunter.databinding.ActivityLandingBinding
import com.raion.hunter.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            tvLogin.setOnClickListener {
                val intentToLogin = Intent(this@SignupActivity, LoginActivity::class.java)
                startActivity(intentToLogin)
            }
            btnGoogle.setOnClickListener {
                val intentToCamera = Intent(this@SignupActivity, CameraActivity::class.java)
                startActivity(intentToCamera)
            }
        }
    }
}