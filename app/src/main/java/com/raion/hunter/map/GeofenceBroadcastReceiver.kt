package com.raion.hunter.map

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import com.raion.hunter.R
import com.raion.hunter.map.FragmentMap.Companion.ACTION_GEOFENCE_EVENT
import com.raion.hunter.util.errorMessage
import com.raion.hunter.util.sendGeofenceEnteredNotification

class GeofenceBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_GEOFENCE_EVENT) {
            val geofencingEvent = GeofencingEvent.fromIntent(intent)

            if (geofencingEvent!!.hasError()) {
                val errorMessage = errorMessage(context, geofencingEvent.errorCode)
                Log.e(TAG, errorMessage)
                return
            }

            if (geofencingEvent.geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
                Log.v(TAG, context.getString(R.string.geofence_entered))
                val fenceId = when {
                    geofencingEvent.triggeringGeofences!!.isNotEmpty() -> geofencingEvent.triggeringGeofences!![0].requestId
                    else -> {
                        Log.e(TAG, "No Geofence Trigger Found! Abort Mission!")
                        return
                    }
                }

                val notificationManager = ContextCompat.getSystemService(
                    context,
                    NotificationManager::class.java
                ) as NotificationManager

                notificationManager.sendGeofenceEnteredNotification(
                    context, fenceId
                )
            }
        }
    }
}

private const val TAG = "GeofenceReceiver"