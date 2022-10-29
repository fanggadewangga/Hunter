package com.raion.hunter.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.raion.hunter.data.datastore.HunterDatastore
import kotlinx.coroutines.Dispatchers

class SplashViewModel(application: Application): AndroidViewModel(application) {
    private val datastore = HunterDatastore.getInstance(application)

    fun getIsFirstTime() = datastore.readPrefHaveRunAppBefore().asLiveData(Dispatchers.IO)
    suspend fun saveIsFirstTime(isFirstTime: Boolean) = datastore.savePrefHaveRunAppBefore(isFirstTime)
}