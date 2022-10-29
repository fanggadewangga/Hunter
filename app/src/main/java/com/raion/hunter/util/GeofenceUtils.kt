package com.raion.hunter.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.maps.model.LatLng
import com.raion.hunter.R
import com.raion.hunter.dto.Place
import com.raion.hunter.dto.PlaceRecommendation
import java.util.concurrent.TimeUnit

fun errorMessage(context: Context, errorCode: Int): String {
    val resources = context.resources
    return when (errorCode) {
        GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE -> resources.getString(
            R.string.geofence_not_available
        )
        GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES -> resources.getString(
            R.string.geofence_too_many_geofences
        )
        GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS -> resources.getString(
            R.string.geofence_too_many_pending_intents
        )
        else -> resources.getString(R.string.unknown_geofence_error)
    }
}

internal object GeofencingConstants {

    /**
     * Used to set an expiration time for a geofence. After this amount of time, Location services
     * stops tracking the geofence. For this sample, geofences expire after one hour.
     */
    val GEOFENCE_EXPIRATION_IN_MILLISECONDS: Long = TimeUnit.HOURS.toMillis(1)

    fun getLandmarkData(context: Context): ArrayList<Place> = arrayListOf(
        Place(
            "kost_cappadocia",
            "Kost Griya Cappadocia",
            "Dummy Description for testing only",
            LatLng(-7.9499, 112.6172),
            ContextCompat.getDrawable(context, R.drawable.iv_kampung_warna)!!
        ),
        Place(
            "universitas_brawijaya",
            "Universitas Brawijaya",
            "The University of Brawijaya, was established on 5 January 1963 and located in Malang. It is an autonomous state university in Indonesia",
            LatLng(-7.952635, 112.6121814),
            ContextCompat.getDrawable(context, R.drawable.iv_kampung_warna)!!
        ),
        Place(
            "sendang_biru",
            "Pantai Sendang Biru",
            "Pantai Sendang Biru merupakan salah satu pantai yang terletak di Desa Sumber Agung, Kecamatan Sumber Manjing Wetan, 69 km ke arah selatan dari pusat Kota Malang",
            LatLng(-8.4322221, 112.6841667),
            ContextCompat.getDrawable(context, R.drawable.iv_kampung_warna)!!
        ),
        Place(
            "balekambang",
            "Balekambang Beach",
            "A temple is reached by a small wooden bridge at high tide at this sandy beach with water activities.",
            LatLng(-8.4035603, 112.534983),
            ContextCompat.getDrawable(context, R.drawable.iv_kampung_warna)!!
        )
    )

    const val GEOFENCE_RADIUS_IN_METERS = 100f
    const val EXTRA_GEOFENCE = "GEOFENCE_EXTRAS"
}

fun buildGeofence(place: Place): Geofence {
    return Geofence.Builder()
        .setRequestId(place.id)
        .setCircularRegion(
            place.latLng.latitude,
            place.latLng.longitude,
            GeofencingConstants.GEOFENCE_RADIUS_IN_METERS
        )
        .setExpirationDuration(GeofencingConstants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
        .build()
}