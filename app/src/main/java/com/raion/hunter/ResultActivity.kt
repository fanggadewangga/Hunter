package com.raion.hunter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TimeUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.raion.hunter.databinding.ActivityResultBinding
import com.raion.hunter.util.GeofencingConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        Log.d("Result Activity", "On new intent")
        val extras = intent?.extras
        if (extras != null) {
            if (extras.containsKey(GeofencingConstants.EXTRA_GEOFENCE)) {
                val placeId = extras.getString(GeofencingConstants.EXTRA_GEOFENCE)!!
                val place = GeofencingConstants.getLandmarkData(this).first {
                    it.id == placeId
                }
                binding.place = place
            }
        }

        val claimButtonListener = ClaimButtonListener {
            lifecycleScope.launch {
                binding.claimButtonText.visibility = View.GONE
                binding.claimButtonProgress.visibility = View.VISIBLE
                delay(2500)
                binding.claimButtonProgress.visibility = View.GONE
                binding.claimButtonChecked.visibility = View.VISIBLE
                Toast.makeText(this@ResultActivity, "Koin berhasil diklaim", Toast.LENGTH_SHORT).show()
                binding.claimButton.isClickable = false
            }
        }

        binding.claimListener = claimButtonListener
    }
}

class ClaimButtonListener(private val clickListener: () -> Unit) {
    fun onClick() = clickListener()
}