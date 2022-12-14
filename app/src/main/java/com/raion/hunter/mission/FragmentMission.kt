package com.raion.hunter.mission

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.raion.hunter.R
import com.raion.hunter.camera.CameraActivity
import com.raion.hunter.databinding.FragmentHomepageBinding
import com.raion.hunter.databinding.FragmentMissionBinding
import com.raion.hunter.dto.DummyPlace
import com.raion.hunter.homepage.PlaceRecommendationAdapter
import com.raion.hunter.homepage.PlaceRecommendationListener

class FragmentMission : Fragment() {

    private lateinit var binding: FragmentMissionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionBinding.inflate(layoutInflater)

        val adapter = PlaceRecommendationAdapter(PlaceRecommendationListener { placeId ->
            findNavController().navigate(FragmentMissionDirections.actionNavigationMissionToLocationDetailFragment(placeId))
        })

        adapter.submitList(DummyPlace.getData(requireContext()).shuffled())
        binding.missionRecommendationRv.adapter = adapter

        binding.missionContainer1.setOnClickListener {
            findNavController().navigate(FragmentMissionDirections.actionNavigationMissionToLeaderboardFragment())
        }

        binding.missionContainer2.setOnClickListener {
            activity?.startActivity(Intent(activity, CameraActivity::class.java))
        }

        return binding.root
    }
}