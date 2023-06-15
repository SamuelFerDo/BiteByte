package com.example.bitebyte.ui.question

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bitebyte.R
import com.example.bitebyte.databinding.FragmentLoginBinding
import com.example.bitebyte.databinding.FragmentQuestionStartBinding
import com.example.bitebyte.utils.SessionManager
import com.google.android.material.snackbar.Snackbar


class QuestionStartFragment : Fragment() {

    private lateinit var sessionManager: SessionManager

    private var _binding: FragmentQuestionStartBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionStartBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.slide_left)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())

        Log.d("HomeFragment", "onViewCreated: ${sessionManager.getAge()}")

        binding.btnStart.setOnClickListener{
            findNavController().navigate(QuestionStartFragmentDirections.actionQuestionStartFragmentToQuestionOneFragment())
        }
    }
}