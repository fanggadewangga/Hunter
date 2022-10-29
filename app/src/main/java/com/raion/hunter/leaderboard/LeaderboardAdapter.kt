package com.raion.hunter.leaderboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raion.hunter.databinding.ItemLeaderboardLayoutBinding
import com.raion.hunter.dto.Leaderboard

class LeaderboardAdapter :
    ListAdapter<Leaderboard, LeaderboardAdapter.LeaderboardViewHolder>(LeaderboarDiffUtilCallback()) {
    inner class LeaderboardViewHolder(private val binding: ItemLeaderboardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Leaderboard) {
            binding.user = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        return LeaderboardViewHolder(ItemLeaderboardLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LeaderboarDiffUtilCallback : DiffUtil.ItemCallback<Leaderboard>() {
    override fun areItemsTheSame(oldItem: Leaderboard, newItem: Leaderboard): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Leaderboard, newItem: Leaderboard): Boolean {
        return oldItem.name == newItem.name
    }
}
