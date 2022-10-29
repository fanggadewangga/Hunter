package com.raion.hunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.raion.hunter.data.UserRepository
import com.raion.hunter.databinding.ActivityMainBinding
import com.raion.hunter.dto.User
import com.raion.hunter.map.MapViewModel
import com.raion.hunter.util.GeofencingConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val user = MutableLiveData<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager.findFragmentById(R.id.frame_layout) as NavHostFragment
        val navController = fragmentManager.navController
        binding.bottomNavigation.setupWithNavController(navController)

        val userRepository = UserRepository(application)

        lifecycleScope.launch(Dispatchers.IO) {
            val tempUser = userRepository.selectUserById(1)
            tempUser?.let {
                withContext(Dispatchers.Main) {
                    user.value = it
                }
            }
        }
    }
}