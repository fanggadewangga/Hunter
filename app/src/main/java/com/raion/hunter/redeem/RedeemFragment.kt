package com.raion.hunter.redeem

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.raion.hunter.MainActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
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
            expandedOffset = 400
            isFitToContents = true
            state = BottomSheetBehavior.STATE_COLLAPSED
        }

        viewmodel.expandSheet.observe(viewLifecycleOwner) {
            if (it) {
                fetchingItem()
            }
        }

        viewmodel.isFinishUpdate.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigateUp()
                viewmodel.onFinishNavigate()
                Toast.makeText(requireContext(), "Redeemed",Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun fetchingItem() {
        bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
        binding.bottomSheetRedeem.visibility = View.VISIBLE

        binding.btnSelectedItem.setOnClickListener {
            viewmodel.updateUserCoins()
            collapseSheet()
        }
    }

    private fun collapseSheet() {
        bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
        binding.bottomSheetRedeem.visibility = View.GONE
    }
}