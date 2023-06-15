package com.example.bitebyte.ui.auth

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.bitebyte.R
import com.example.bitebyte.data.model.ApiResult
import com.example.bitebyte.databinding.FragmentLoginBinding
import com.example.bitebyte.utils.SessionManager
import com.example.bitebyte.utils.viewModelsFactory
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var sessionManager : SessionManager

    private val loginViewModel: LoginViewModel by viewModelsFactory {
        sessionManager = SessionManager(requireContext())
        LoginViewModel(requireActivity().application, sessionManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = loginViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        loginViewModel.login.observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.GONE
            when(it){
                is ApiResult.Success -> {
                    if(sessionManager.getFillInput() == null){
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToQuestionStartFragment())
                        Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                    }else{
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                        Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                    }
                }
                is ApiResult.Error -> {
                    Snackbar.make(binding.root, it.error, Snackbar.LENGTH_SHORT).show()
                }
                is ApiResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        binding.tvCreateAccount.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_create_account ->{
                val extras = FragmentNavigatorExtras(
                    binding.containerFormLogin to "formContainer"
                )
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment(), extras)
            }
        }
    }

}