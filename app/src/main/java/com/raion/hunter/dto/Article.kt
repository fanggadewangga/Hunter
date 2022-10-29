package com.raion.hunter.dto

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.raion.hunter.R

data class Article(
    val id: String,
    val title: String,
    val image: Drawable,
    val publishedDate: String,
)

object DummyArticle {
    fun getData(context: Context): ArrayList<Article> {
        return arrayListOf(
            Article(
                id = "1",
                title = "5 destinasi terbaik di malang",
                image = ContextCompat.getDrawable(context, R.drawable.iv_tugu_malang)!!,
                publishedDate = "12 Oktober 2022"
            ),
            Article(
                id = "2",
                title = "Rekomendasi kulineran di Malang",
                image = ContextCompat.getDrawable(context, R.drawable.balekambang)!!,
                publishedDate = "14 Agustus 2022"
            ),
            Article(
                id = "3",
                title = "Hidden gems kuliner di daerah UB",
                image = ContextCompat.getDrawable(context, R.drawable.iv_ub)!!,
                publishedDate = "13 Juni 2022"
            ),
            Article(
                id = "4",
                title = "3 tempat liburan keluarga di Malang",
                image = ContextCompat.getDrawable(context, R.drawable.iv_pantai_gatra)!!,
                publishedDate = "13 Juni 2022"
            ),
            Article(
                id = "5",
                title = "Pesona kota Malang",
                image = ContextCompat.getDrawable(context, R.drawable.iv_alun_alun_malang)!!,
                publishedDate = "19 September 2022"
            ),
        )
    }
}
