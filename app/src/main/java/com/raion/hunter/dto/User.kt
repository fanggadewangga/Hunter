package com.raion.hunter.dto

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val name: String,
    val coins: Int,
)