package com.raion.hunter.redeem

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.hunter.data.UserRepository
import com.raion.hunter.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RedeemViewmodel(application: Application): ViewModel() {

    val user = MutableLiveData<User>()
    private val repository = UserRepository(application)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val tempUser = repository.selectUserById(1)
            tempUser?.let {
                withContext(Dispatchers.Main) {
                    user.value = it
                }
            }
        }
    }

    fun redeemCoin() {
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCoins(user.value!!.coins - 250, user.value!!.uid!!)
        }
    }
}