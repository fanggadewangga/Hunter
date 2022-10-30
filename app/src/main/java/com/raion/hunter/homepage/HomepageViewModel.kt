package com.raion.hunter.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.hunter.data.UserRepository
import com.raion.hunter.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomepageViewModel(private val repository: UserRepository): ViewModel() {


    val user = MutableLiveData<User>()

    init {
        fetchUserData()
    }

    fun fetchUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val tempUser = repository.selectUserById(1)
            tempUser?.let {
                withContext(Dispatchers.Main) {
                    user.value = it
                }
            }
        }
    }
}