package com.raion.hunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.raion.hunter.databinding.ActivityMainBinding
import com.raion.hunter.homepage.FragmentHomepageDirections
import com.raion.hunter.map.FragmentMapDirections
import com.raion.hunter.map.MapViewModel
import com.raion.hunter.util.GeofencingConstants

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager.findFragmentById(R.id.frame_layout) as NavHostFragment
        navController = fragmentManager.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("MainActivity", "On new intent")
        val extras = intent?.extras
        if (extras != null) {
            if (extras.containsKey(GeofencingConstants.EXTRA_GEOFENCE)) {
                val placeId = extras.getString(GeofencingConstants.EXTRA_GEOFENCE)!!
                Toast.makeText(this, "Pressed Notification on fence $placeId", Toast.LENGTH_SHORT).show()
                navController.navigate(FragmentHomepageDirections.actionNavigationHomeToClaimFragment(placeId))
            }
        }
    }
}