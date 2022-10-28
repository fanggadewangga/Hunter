package com.raion.hunter.onboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raion.hunter.MainActivity
import com.raion.hunter.databinding.ActivitySecondOnboardBinding

class SecondOnboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondOnboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondOnboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            val intentToMain = Intent(this@SecondOnboardActivity, MainActivity::class.java)
            startActivity(intentToMain)
        }
    }
}