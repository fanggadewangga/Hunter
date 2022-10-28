package com.raion.hunter.dto

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.raion.hunter.R

data class PlaceRecommendation(
    val name: String,
    val image: Drawable
)

object DummyPlace {
    fun getData(context: Context): ArrayList<PlaceRecommendation> {
        return arrayListOf(
            PlaceRecommendation("Kampung Warna Warni", ContextCompat.getDrawable(context, R.drawable.iv_kampung_warna)!!),
            PlaceRecommendation("Kampung Warna Warni", ContextCompat.getDrawable(context, R.drawable.iv_kampung_warna)!!),
            PlaceRecommendation("Kampung Warna Warni", ContextCompat.getDrawable(context, R.drawable.iv_kampung_warna)!!),
            PlaceRecommendation("Kampung Warna Warni", ContextCompat.getDrawable(context, R.drawable.iv_kampung_warna)!!),
            PlaceRecommendation("Kampung Warna Warni", ContextCompat.getDrawable(context, R.drawable.iv_kampung_warna)!!),
            PlaceRecommendation("Kampung Warna Warni", ContextCompat.getDrawable(context, R.drawable.iv_kampung_warna)!!),
        )
    }
}