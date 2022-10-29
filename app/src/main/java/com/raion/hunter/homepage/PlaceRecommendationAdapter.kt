package com.raion.hunter.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raion.hunter.databinding.ItemRecommendationLayoutBinding
import com.raion.hunter.dto.PlaceRecommendation

class PlaceRecommendationAdapter(private val placeRecommendationListener: PlaceRecommendationListener): ListAdapter<PlaceRecommendation, PlaceRecommendationAdapter.PlaceRecommendationViewHolder>(PlaceRecommendationDiffUtilCallback()) {

    inner class PlaceRecommendationViewHolder(private val binding: ItemRecommendationLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlaceRecommendation) {
            binding.place = item
            binding.listener = placeRecommendationListener
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceRecommendationViewHolder {
         return PlaceRecommendationViewHolder(ItemRecommendationLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlaceRecommendationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlaceRecommendationListener(val clickListener: (placeId: String) -> Unit) {
    fun onClick(placeId: String) = clickListener(placeId)
}

class PlaceRecommendationDiffUtilCallback(): DiffUtil.ItemCallback<PlaceRecommendation>() {
    override fun areItemsTheSame(
        oldItem: PlaceRecommendation,
        newItem: PlaceRecommendation
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: PlaceRecommendation,
        newItem: PlaceRecommendation
    ): Boolean {
        return oldItem.name == newItem.name
    }
}