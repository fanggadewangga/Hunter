package com.raion.hunter.homepage

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.raion.hunter.BuildConfig
import com.raion.hunter.MainActivity
import com.raion.hunter.R
import com.raion.hunter.data.UserRepository
import com.raion.hunter.databinding.FragmentHomepageBinding
import com.raion.hunter.dto.DummyPlace
import java.util.*


class FragmentHomepage : Fragment() {

    private lateinit var binding: FragmentHomepageBinding
    private lateinit var viewModel: HomepageViewModel
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val deviceQLater = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q

    private lateinit var repository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomepageBinding.inflate(layoutInflater)
        repository = UserRepository(requireActivity().application)
        val factory = HomepageViewmodelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[HomepageViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val adapter = PlaceRecommendationAdapter(PlaceRecommendationListener { placeId ->
            findNavController().navigate(FragmentHomepageDirections.actionNavigationHomeToLocationDetailFragment(placeId))
        })
        adapter.submitList(DummyPlace.getData(requireContext()))
        binding.placeRecommendationRv.adapter = adapter

        binding.exchangeButton.setOnClickListener {
            findNavController().navigate(FragmentHomepageDirections.actionNavigationHomeToRedeemFragment())
        }

        getLastLocation()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchUserData()
    }

    private fun getLastLocation() {
        val foregroundPermission = (ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)

        val backgroundPermission = if (deviceQLater) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        } else true

        if (foregroundPermission && backgroundPermission) {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener {
                    val geoCoder = Geocoder(requireContext(), Locale.getDefault())
                    if (Geocoder.isPresent()) {
                        val addresses: List<Address>? = geoCoder.getFromLocation(it.latitude, it.longitude, 1)
                        if (addresses!!.isNotEmpty()) {
                            // obtain all information from addresses.get(0)
                            binding.locationText.text = addresses[0].locality
                        }
                    }
                }
        } else {
            requestForegroundAndBackgroundLocationPermissions()
        }
    }

    @TargetApi(29)
    private fun requestForegroundAndBackgroundLocationPermissions() {
        if (foregroundAndBackgroundLocationPermissionApproved()) return
        var permissionArray = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

        val requestCode = when {
            deviceQLater -> {
                permissionArray += Manifest.permission.ACCESS_BACKGROUND_LOCATION
                REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE
            }
            else -> {
                REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
            }
        }

        requestPermissionLauncher.launch(permissionArray)
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isGranted = permissions.entries.all {
            Log.d(TAG, it.key + " value : " + it.value.toString())
            it.value
        }
        if (isGranted) {
            Log.d(TAG, "On request permission result Approved")
            getLastLocation()
        } else {
            // Permission Denied
            Log.d(TAG, "Asking Permission Denied")
            Snackbar.make(
                binding.homepageFragment,
                R.string.permission_denied_explanation,
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction(
                    R.string.settings
                ) {
                    startActivity(
                        Intent().apply {
                            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                    )
                }
                .show()
        }
    }

    @TargetApi(29)
    private fun foregroundAndBackgroundLocationPermissionApproved(): Boolean {
        val foregroundPermission = (ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)

        val backgroundPermission = if (deviceQLater) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        } else true

        return foregroundPermission && backgroundPermission
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }
}
private const val REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE = 33
private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
private const val REQUEST_TURN_DEVICE_LOCATION_ON = 29
private const val TAG = "HomepageFragment"