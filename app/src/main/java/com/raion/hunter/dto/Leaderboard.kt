package com.raion.hunter.dto

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.raion.hunter.R

data class Leaderboard(
    val name: String,
    val point: Int,
    val rank: Int,
    val avatar: Drawable
)

object DummyLeaderboard {
    fun getData(context: Context): ArrayList<Leaderboard> {
        return arrayListOf(
            Leaderboard(
                name = "Jepri",
                point = 1000,
                rank = 4,
                avatar = ContextCompat.getDrawable(context, R.drawable.iv_leaderboard_4)!!
            ),
            Leaderboard(
                name = "Achmad",
                point = 900,
                rank = 5,
                avatar = ContextCompat.getDrawable(context, R.drawable.iv_leaderborad_5)!!
            ),
            Leaderboard(
                name = "Abimanyu",
                point = 870,
                rank = 6,
                avatar = ContextCompat.getDrawable(context, R.drawable.iv_leaderboard_6)!!
            )
        )
    }
}