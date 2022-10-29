package com.raion.hunter.claim

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raion.hunter.R
import com.raion.hunter.databinding.FragmentClaimBinding
import com.raion.hunter.util.GeofencingConstants

class ClaimFragment : Fragment() {

    private lateinit var binding: FragmentClaimBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentClaimBinding.inflate(layoutInflater)

        val placeId = arguments?.getString(PLACE_ID_SAFEARGS)
        val place = GeofencingConstants.getLandmarkData(requireContext()).first {
            it.id == placeId
        }

        binding.place = place


        return binding.root
    }
}

const val PLACE_ID_SAFEARGS = "SafeArgsKey"