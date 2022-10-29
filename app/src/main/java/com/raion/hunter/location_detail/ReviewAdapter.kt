package com.raion.hunter.location_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raion.hunter.databinding.ItemReviewLayoutBinding
import com.raion.hunter.dto.Review

class ReviewAdapter :
    ListAdapter<Review, ReviewAdapter.ReviewViewHolder>(
        ReviewDiffUtilCallback()) {

    inner class ReviewViewHolder(private val binding: ItemReviewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Review) {
            binding.review = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ReviewViewHolder {
        return ReviewViewHolder(ItemReviewLayoutBinding.inflate(LayoutInflater.from(
            parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ReviewListener(val clickListener: (reviewId: String) -> Unit) {
    fun onClick(reviewId: String) = clickListener(reviewId)
}

class ReviewDiffUtilCallback : DiffUtil.ItemCallback<Review>() {
    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.name == newItem.name
    }
}