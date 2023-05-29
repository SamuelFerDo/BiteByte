package com.example.bitebyte.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bitebyte.R
import com.example.bitebyte.databinding.FragmentOnBoardingBinding

class onBoardingFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener(this)
        binding.btnSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_sign_in ->{
                findNavController().navigate(onBoardingFragmentDirections.actionOnBoardingFragmentToLoginFragment())
            }
            R.id.btn_sign_up ->{
                findNavController().navigate(onBoardingFragmentDirections.actionOnBoardingFragmentToRegisterFragment())
            }
        }
    }
}