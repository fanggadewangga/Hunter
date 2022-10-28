package com.raion.hunter.map

import android.Manifest
import android.annotation.TargetApi
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import com.raion.hunter.BuildConfig
import com.raion.hunter.R
import com.raion.hunter.databinding.FragmentMapBinding
import com.raion.hunter.dto.Place
import com.raion.hunter.util.GeofencingConstants
import com.raion.hunter.util.buildGeofence
import com.raion.hunter.util.createChannel

class FragmentMap : Fragment() {

    private lateinit var binding: FragmentMapBinding
    private lateinit var viewModel: MapViewModel
    private lateinit var geofencingClient: GeofencingClient
    private lateinit var place: Place
    private val geofenceList = mutableListOf<Geofence>()

    private val deviceQLater = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q

    // A PendingIntent for the Broadcast Receiver that handles geofence transitions.
    private val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(requireActivity(), GeofenceBroadcastReceiver::class.java)
        intent.action = ACTION_GEOFENCE_EVENT
        PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        geofencingClient = LocationServices.getGeofencingClient(requireActivity())
        Log.d(TAG, "Instantiating geofencing client")
    }

    override fun onStart() {
        super.onStart()
        checkPermissionsAndStartGeofencing()
        Log.d(TAG, "Starting Geofencing Check")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MapViewModel::class.java]

        place = GeofencingConstants.LANDMARK_DATA[arguments?.getInt("landmarkIndex")!!]

        viewModel._onNewIntent.observe(viewLifecycleOwner) {
            onNewIntent(it)
        }

        GeofencingConstants.LANDMARK_DATA.forEach { place ->
            geofenceList.add(buildGeofence(place))
        }

        createChannel(requireContext())

        return binding.root
    }

    private fun onNewIntent(intent: Intent?) {
        val extras = intent?.extras
        if (extras != null) {
            if (extras.containsKey(GeofencingConstants.EXTRA_GEOFENCE)) {
                Toast.makeText(requireContext(), "Pressed Notification on fence ${extras.getString(GeofencingConstants.EXTRA_GEOFENCE)}", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "On new intent")
                checkPermissionsAndStartGeofencing()
            }
        }
    }

    private fun checkPermissionsAndStartGeofencing() {
        Log.d(TAG, "Checking Permission")
        if (viewModel.geofenceIsActive()) return
        Log.d(TAG, "Geofencing not active")
        if (foregroundAndBackgroundLocationPermissionApproved()) {
            Log.d(TAG, "Approved")
            checkDeviceLocationSettingsAndStartGeofence()
        } else {
            Log.d(TAG, "Requesting")
            requestForegroundAndBackgroundLocationPermissions()
        }
    }

    private fun getGeofencingRequest(): GeofencingRequest {
        return GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            addGeofences(geofenceList)
        }.build()
    }

    private fun checkDeviceLocationSettingsAndStartGeofence(resolve: Boolean = true) {
        Log.d(TAG, "Checking Location")
        val locationRequest = LocationRequest.create().apply {
            priority = Priority.PRIORITY_LOW_POWER
        }
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val settingsClient = LocationServices.getSettingsClient(requireActivity())
        val locationSettingResponseTask = settingsClient.checkLocationSettings(builder.build())
        locationSettingResponseTask.addOnFailureListener { exception ->
            if (exception is ResolvableApiException && resolve) {
                try {
                    exception.startResolutionForResult(requireActivity(), REQUEST_TURN_DEVICE_LOCATION_ON)
                } catch (sendEx: IntentSender.SendIntentException) {
                    Log.d(TAG, "Error getting location settings resolution: " + sendEx.message)
                }
            } else {
                Snackbar.make(
                    binding.mapFragment,
                    R.string.location_required_error,
                    Snackbar.LENGTH_INDEFINITE
                ).setAction(android.R.string.ok) {
                    checkDeviceLocationSettingsAndStartGeofence()
                }.show()
            }
        }

        locationSettingResponseTask.addOnCompleteListener {
            addGeofences()
        }
    }

    private fun addGeofences() {
        Log.d(TAG, "Adding Geofences")
        if (viewModel.geofenceIsActive()) return
        geofencingClient.removeGeofences(geofencePendingIntent).run {
            addOnCompleteListener {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestForegroundAndBackgroundLocationPermissions()
                    return@addOnCompleteListener
                }
                geofencingClient.addGeofences(getGeofencingRequest(), geofencePendingIntent).run {
                    addOnSuccessListener {
                        Log.d(TAG, "Adding Success")
                        Toast.makeText(requireContext(), R.string.geofences_added, Toast.LENGTH_SHORT).show()
                        viewModel.geofenceActivated()
                    }
                    addOnFailureListener {
                        Log.d(TAG, "Adding Failed")
                        Toast.makeText(requireContext(), R.string.geofences_not_added, Toast.LENGTH_SHORT).show()
                        if (it.message != null) {
                            Log.w(TAG, it.message!!)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        removeGeofences()
    }


    private fun removeGeofences() {
        if (!foregroundAndBackgroundLocationPermissionApproved()) {
            return
        }

        geofencingClient.removeGeofences(geofencePendingIntent).run {
            addOnSuccessListener {
                Log.d(TAG, getString(R.string.geofences_removed))
                Toast.makeText(requireContext(), R.string.geofences_removed, Toast.LENGTH_SHORT).show()
            }

            addOnFailureListener {
                Log.d(TAG, getString(R.string.geofences_not_removed))
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "On Request Permission Result")
        if (grantResults.isEmpty() ||
            grantResults[LOCATION_PERMISSION_INDEX] == PackageManager.PERMISSION_DENIED ||
            (requestCode == REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE &&
                    grantResults[BACKGROUND_LOCATION_PERMISSION_INDEX] == PackageManager.PERMISSION_DENIED)
        ) {
            // Permission Denied
            Log.d(TAG, "Asking Permission Denied")
            Snackbar.make(
                binding.mapFragment,
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
        } else {
            Log.d(TAG, "On request permission result Approved")
            checkDeviceLocationSettingsAndStartGeofence()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TURN_DEVICE_LOCATION_ON) {
            checkDeviceLocationSettingsAndStartGeofence(false)
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
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        } else true

        return foregroundPermission && backgroundPermission
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
            checkDeviceLocationSettingsAndStartGeofence()
        } else {
            // Permission Denied
            Log.d(TAG, "Asking Permission Denied")
            Snackbar.make(
                binding.mapFragment,
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
    private fun requestForegroundAndBackgroundLocationPermissions() {
        Log.d(TAG, "Asking Permission Check")
        if (foregroundAndBackgroundLocationPermissionApproved()) return
        Log.d(TAG, "Asking Permission Check Approved")
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

        Log.d(TAG, "Asking Permission Main")
        requestPermissionLauncher.launch(permissionArray)
    }

    companion object {
        internal const val ACTION_GEOFENCE_EVENT =
            "MapFragment.hunter.action.ACTION_GEOFENCE_EVENT"
    }
}

private const val REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE = 33
private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
private const val REQUEST_TURN_DEVICE_LOCATION_ON = 29
private const val TAG = "MapFragment"
private const val LOCATION_PERMISSION_INDEX = 0
private const val BACKGROUND_LOCATION_PERMISSION_INDEX = 1