package com.raion.hunter.dto

import android.graphics.drawable.Drawable
import com.google.android.gms.maps.model.LatLng

data class Place(
    val id: String,
    val name: String,
    val description: String,
    val latLng: LatLng,
    val image: Drawable
)
