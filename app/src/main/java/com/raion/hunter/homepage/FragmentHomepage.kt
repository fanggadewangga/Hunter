package com.raion.hunter.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raion.hunter.R
import com.raion.hunter.databinding.FragmentHomepageBinding
import com.raion.hunter.dto.DummyPlace

class FragmentHomepage : Fragment() {

    private lateinit var binding: FragmentHomepageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomepageBinding.inflate(layoutInflater)

        val adapter = PlaceRecommendationAdapter()
        adapter.submitList(DummyPlace.getData(requireContext()))
        binding.placeRecommendationRv.adapter = adapter


        return binding.root
    }
}