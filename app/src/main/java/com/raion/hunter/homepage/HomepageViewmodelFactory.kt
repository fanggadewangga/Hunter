package com.raion.hunter.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raion.hunter.data.UserRepository

class HomepageViewmodelFactory(private val repository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomepageViewModel::class.java)) {
            return HomepageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class")
    }
}