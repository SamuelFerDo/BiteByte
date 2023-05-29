package com.example.bitebyte.ui.bottomnav.ui.recommendation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.bitebyte.R
import com.example.bitebyte.databinding.FragmentHomeBinding
import com.example.bitebyte.databinding.FragmentRecommendationBinding
import com.example.bitebyte.ui.bottomnav.ui.home.HomeViewModel

class RecommendationFragment : Fragment() {

    private var _binding: FragmentRecommendationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(RecommendationViewModel::class.java)

        _binding = FragmentRecommendationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textRecommendation
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}