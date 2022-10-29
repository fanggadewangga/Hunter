package com.raion.hunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.raion.hunter.databinding.ActivityResultBinding
import com.raion.hunter.map.FragmentMap
import com.raion.hunter.util.GeofencingConstants

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
                FragmentMap().removeGeofences()
            }
        }

        binding.claimButton.setOnClickListener {
            // Claimed -> add user coins
        }
    }
}