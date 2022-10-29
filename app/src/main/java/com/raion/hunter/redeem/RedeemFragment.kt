package com.raion.hunter.redeem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.raion.hunter.R
import com.raion.hunter.databinding.FragmentRedeemBinding

class RedeemFragment : Fragment() {

    private lateinit var binding: FragmentRedeemBinding
    private lateinit var bottomSheet: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRedeemBinding.inflate(layoutInflater)
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