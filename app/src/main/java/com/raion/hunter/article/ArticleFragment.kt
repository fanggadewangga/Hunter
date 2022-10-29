package com.raion.hunter.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.raion.hunter.databinding.FragmentArticleBinding
import com.raion.hunter.dto.DummyArticle


class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentArticleBinding.inflate(layoutInflater)
        val adapter = ArticleAdapter()
        adapter.submitList(DummyArticle.getData(requireContext()))
        binding.rvArticle.adapter = adapter
        return binding.root
    }
}