package com.raion.hunter.data

import android.app.Application
import com.raion.hunter.dto.User

class UserRepository(application: Application) {
    private val dao: UserDao

    init {
        val database: UserDatabase = UserDatabase.getInstance(application)
        dao = database.userDao()
    }

    suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    suspend fun selectUserById(uid: Int): User? {
        return dao.selectUser(uid)
    }

    suspend fun updateCoins(coins: Int, uid: Int) {
        dao.updateCoins(coins, uid)
    }

    suspend fun getAllUser(): List<User> {
        return dao.getAllUser()
    }
}