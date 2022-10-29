package com.raion.hunter.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raion.hunter.dto.User

class HomepageViewModel: ViewModel() {
    val user = MutableLiveData<User>()
}