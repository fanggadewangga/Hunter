package com.raion.hunter.dto

import android.graphics.drawable.Drawable

data class Article(
    val id: String,
    val title: String,
    val image: Drawable,
    val publishedDate: String
)
