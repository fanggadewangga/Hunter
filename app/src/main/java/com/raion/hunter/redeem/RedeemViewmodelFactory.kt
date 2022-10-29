package com.raion.hunter.redeem

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RedeemViewmodelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RedeemViewmodel::class.java)) {
            return RedeemViewmodel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}