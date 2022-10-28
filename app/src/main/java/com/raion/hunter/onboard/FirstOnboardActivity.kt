package com.raion.hunter.onboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raion.hunter.databinding.ActivityFirstOnboardBinding

class FirstOnboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstOnboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFirstOnboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            btnNext.setOnClickListener {
                val intentToSecondOnboard =
                    Intent(this@FirstOnboardActivity, SecondOnboardActivity::class.java)
                startActivity(intentToSecondOnboard)
            }
        }
    }
}