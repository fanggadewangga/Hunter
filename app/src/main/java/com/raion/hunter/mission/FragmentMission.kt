package com.raion.hunter.mission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raion.hunter.R
import com.raion.hunter.databinding.FragmentHomepageBinding
import com.raion.hunter.databinding.FragmentMissionBinding
import com.raion.hunter.dto.DummyPlace
import com.raion.hunter.homepage.PlaceRecommendationAdapter

class FragmentMission : Fragment() {

    private lateinit var binding: FragmentMissionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionBinding.inflate(layoutInflater)

        val adapter = PlaceRecommendationAdapter()
        adapter.submitList(DummyPlace.getData(requireContext()))
        binding.missionRecommendationRv.adapter = adapter




        return binding.root
    }
}