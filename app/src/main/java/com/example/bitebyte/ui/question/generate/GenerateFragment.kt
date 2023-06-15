package com.example.bitebyte.ui.question.generate

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bitebyte.data.model.ApiResult
import com.example.bitebyte.databinding.FragmentGenerateBinding
import com.example.bitebyte.utils.SessionManager
import com.example.bitebyte.utils.viewModelsFactory
import com.google.android.material.snackbar.Snackbar

class GenerateFragment : Fragment() {

    private val args: GenerateFragmentArgs by navArgs()

    private lateinit var age : String
    private lateinit var gender : String
    private lateinit var weight : String
    private lateinit var height: String
    private lateinit var healthConcern: String
    private lateinit var menuType: String
    private lateinit var activity: String

    private lateinit var sessionManager: SessionManager

    private var _binding: FragmentGenerateBinding? = null
    private val binding get() = _binding!!

    private var valueAnimator: ValueAnimator? = null

    private val viewModel: GenerateViewModel by viewModelsFactory {
        sessionManager = SessionManager(requireContext())
        GenerateViewModel(requireActivity().application, sessionManager ,age, weight,height,gender, healthConcern, menuType, activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenerateBinding.inflate(inflater, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.slide_left)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        age = args.age
        weight = args.weight
        height = args.height
        gender = args.gender
        healthConcern  = args.healthConcern
        menuType = args.menuType
        activity = args.activity

        viewModel.userData.observe(viewLifecycleOwner){
            when(it){
                is ApiResult.Success -> {
                    findNavController().navigate(GenerateFragmentDirections.actionGenerateFragmentToHomeFragment())
                }
                is ApiResult.Error -> {
                    Snackbar.make(binding.root, it.error, Snackbar.LENGTH_SHORT).show()
                }
                is ApiResult.Loading -> {
                }
            }
        }

        valueAnimator = ValueAnimator.ofInt(0, 100).apply {
            addUpdateListener {
                binding.tvPercentage.text = "${it.animatedValue}%"
            }
            interpolator = LinearInterpolator()
            duration = 5000
            start()
        }
        valueAnimator?.doOnEnd {
            Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launchWhenResumed {
                sessionManager.addFillInput("true")
                viewModel.postUserData()
            }
        }, 500)
        }
    }
}