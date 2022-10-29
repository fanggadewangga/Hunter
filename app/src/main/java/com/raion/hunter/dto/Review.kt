package com.raion.hunter.dto

import android.content.Context

data class Review(
    val reviewId: String,
    val name: String,
    val review: String,
)

object DummyReview {
    fun getData(context: Context): ArrayList<Review> {
        return arrayListOf(
            Review(
                reviewId = "1",
                name = "Arief A",
                review = "Suasananya sangat asik dan seru"
            ),
            Review(
                reviewId = "2",
                name = "Widodo",
                review = "Recomended banget nihhh"
            ),
            Review(
                reviewId = "3",
                name = "Luhut",
                review = "Wahhh keren banget"
            ),
            Review(
                reviewId = "3",
                name = "Achmad",
                review = "Tempatnya sangat instagramable"
            ),
            Review(
                reviewId = "4",
                name = "Saputra",
                review = "Rilll cuyh tempatnya"
            ),
            Review(
                reviewId = "5",
                name = "Irfan",
                review = "Pengen kesini sama doi"
            ),
            Review(
                reviewId = "6",
                name = "Dewanto",
                review = "Cocok banget buat liburan"
            ),
        )
    }
}