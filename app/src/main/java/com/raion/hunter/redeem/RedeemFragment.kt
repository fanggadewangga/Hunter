package com.raion.hunter.redeem

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.raion.hunter.MainActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.raion.hunter.R
import com.raion.hunter.data.UserRepository
import com.raion.hunter.databinding.FragmentRedeemBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RedeemFragment : Fragment() {

    private lateinit var viewmodel: RedeemViewmodel
    private lateinit var binding: FragmentRedeemBinding
    private lateinit var bottomSheet: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRedeemBinding.inflate(layoutInflater)
        val factory = RedeemViewmodelFactory(requireActivity().application)
        viewmodel = ViewModelProvider(this, factory)[RedeemViewmodel::class.java]
        binding.lifecycleOwner = this

        binding.viewmodel = viewmodel

        bottomSheet = BottomSheetBehavior.from(binding.bottomSheetRedeem).apply {
            expandedOffset = 120
            peekHeight = 640
            isFitToContents = false

            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {

                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    TODO("Not yet implemented")
                }
            }
            )
        }

        return binding.root
    }
}