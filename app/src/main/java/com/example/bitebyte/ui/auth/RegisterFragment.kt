package com.example.bitebyte.ui.auth

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.bitebyte.R
import com.example.bitebyte.data.model.ApiResult
import com.example.bitebyte.databinding.FragmentRegisterBinding
import com.example.bitebyte.utils.viewModelsFactory
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel: RegisterViewModel by viewModelsFactory {
        RegisterViewModel(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = registerViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        registerViewModel.register.observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.GONE
            when(it){
                is ApiResult.Success -> {
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                    Toast.makeText(requireContext(), "Register Successful", Toast.LENGTH_SHORT).show()
                }
                is ApiResult.Error -> {
                    Snackbar.make(binding.root, it.error, Snackbar.LENGTH_SHORT).show()
                }
                is ApiResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        binding.tvLogin.setOnClickListener(this)
        binding.ibBackButton.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_login -> {
                val extras = FragmentNavigatorExtras(
                    binding.containerFormRegister to "formContainer"
                )
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(), extras)
            }
            R.id.ib_back_button ->{
                findNavController().navigateUp()
            }
        }
    }
}