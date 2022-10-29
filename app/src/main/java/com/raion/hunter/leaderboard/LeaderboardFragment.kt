package com.raion.hunter.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.raion.hunter.databinding.FragmentLeaderboardBinding
import com.raion.hunter.dto.DummyLeaderboard


class LeaderboardFragment : Fragment() {
    private lateinit var binding: FragmentLeaderboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLeaderboardBinding.inflate(layoutInflater)
        val adapter = LeaderboardAdapter()
        adapter.submitList(DummyLeaderboard.getData(requireContext()))
        binding.rvLeaderboard.adapter = adapter
        return binding.root
    }
}