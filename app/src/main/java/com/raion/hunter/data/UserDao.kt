package com.raion.hunter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.raion.hunter.dto.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE uid = :uid")
    suspend fun selectUser(uid: Int): User?

    @Query("UPDATE user SET coins = :newCoins WHERE uid = :uid")
    suspend fun updateCoins(newCoins : Int, uid: Int )

    @Query("SELECT * FROM user")
    suspend fun getAllUser(): List<User>
}