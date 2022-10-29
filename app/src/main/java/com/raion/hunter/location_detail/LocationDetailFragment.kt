package com.raion.hunter.location_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.raion.hunter.R
import com.raion.hunter.databinding.FragmentLocationDetailBinding
import com.raion.hunter.util.GeofencingConstants

class LocationDetailFragment : Fragment() {

    private lateinit var viewModel: LocationDetailViewModel
    private lateinit var binding: FragmentLocationDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLocationDetailBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[LocationDetailViewModel::class.java]

        val placeId = arguments?.getString("placeId")
        val place = GeofencingConstants.getLandmarkData(requireContext()).first {
            it.id == placeId
        }

        binding.place = place
        binding.lifecycleOwner = this

        binding.visitPlaceDetail.setOnClickListener {
            findNavController().navigate(LocationDetailFragmentDirections.actionLocationDetailFragmentToNavigationMap(place.id))
        }

        return binding.root
    }
}