package com.raion.hunter.dto

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.LatLng
import com.raion.hunter.R

data class PlaceRecommendation(
    val id: String,
    val name: String,
    val image: Drawable
)

object DummyPlace {
    fun getData(context: Context): ArrayList<PlaceRecommendation> {
        return arrayListOf(
            PlaceRecommendation(
                "kost_cappadocia",
                "Kost Griya Cappadocia",
                ContextCompat.getDrawable(context, R.drawable.iv_kost_griya)!!
            ),
            PlaceRecommendation(
                "kampung_warna",
                "Kampung Warna Warni",
                ContextCompat.getDrawable(context, R.drawable.iv_kampung_warna_warni)!!
            ),
            PlaceRecommendation(
                "universitas_brawijaya",
                "Universitas Brawijaya",
                ContextCompat.getDrawable(context, R.drawable.iv_ub)!!
            ),
            PlaceRecommendation(
                "sendang_biru",
                "Pantai Sendang Biru",
                ContextCompat.getDrawable(context, R.drawable.iv_sendang_biru)!!
            ),
            PlaceRecommendation(
                "balekambang",
                "Balekambang Beach",
                ContextCompat.getDrawable(context, R.drawable.balekambang)!!
            ),
            PlaceRecommendation(
                "alun_alun_malang",
                "Alun Alun Kota Malang",
                ContextCompat.getDrawable(context, R.drawable.iv_alun_alun_malang)!!
            )
        )
    }
}