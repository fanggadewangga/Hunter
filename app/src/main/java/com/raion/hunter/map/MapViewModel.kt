package com.raion.hunter.map

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MapViewModel(private val state: SavedStateHandle): ViewModel() {
    private val _geofenceStatus = state.getLiveData(GEOFENCE_STATUS_KEY, false)
    val geofenceStatus: LiveData<Boolean>
        get() = _geofenceStatus

    fun geofenceIsActive() = _geofenceStatus.value!!
    fun geofenceActivated() {
        _geofenceStatus.value = true
        state[GEOFENCE_STATUS_KEY] = true
        Log.d("MapViewModel", "Geofence activated")
    }

    val _onNewIntent = MutableLiveData<Intent>()
}

private const val HINT_INDEX_KEY = "hintIndex"
private const val GEOFENCE_STATUS_KEY = "geofenceActive"