package com.raion.hunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.raion.hunter.databinding.ActivityMainBinding
import com.raion.hunter.map.MapViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mapViewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager.findFragmentById(R.id.frame_layout) as NavHostFragment
        val navController = fragmentManager.navController

        binding.bottomNavigation.setupWithNavController(navController)

        mapViewModel = ViewModelProvider(this)[MapViewModel::class.java]
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mapViewModel._onNewIntent.value = intent
    }
}