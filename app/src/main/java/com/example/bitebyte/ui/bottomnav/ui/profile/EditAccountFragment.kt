package com.example.bitebyte.ui.bottomnav.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bitebyte.R
import com.example.bitebyte.data.model.ApiResult
import com.example.bitebyte.databinding.FragmentEditAccountBinding
import com.example.bitebyte.ui.auth.RegisterFragmentDirections
import com.example.bitebyte.utils.SessionManager
import com.example.bitebyte.utils.viewModelsFactory
import com.google.android.material.snackbar.Snackbar

class EditAccountFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentEditAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var sessionManager: SessionManager

    private val viewModel: EditAccountViewModel by viewModelsFactory {
        sessionManager = SessionManager(requireContext())
        EditAccountViewModel(requireActivity().application, sessionManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditAccountBinding.inflate(inflater, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.editAccount.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            when(it) {
                is ApiResult.Success -> {
                    findNavController().navigate(EditAccountFragmentDirections.actionEditAccountFragmentToProfileFragment())
                    Toast.makeText(requireContext(), "Edit Account Successful", Toast.LENGTH_SHORT).show()
                }

                is ApiResult.Error -> {
                    Snackbar.make(binding.root, it.error, Snackbar.LENGTH_SHORT).show()
                }

                is ApiResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

        binding.btnUpdate.setOnClickListener(this)
        binding.ibBackButton.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        binding.etName.setText(sessionManager.getName())
        binding.etEmail.setText(sessionManager.getEmail())
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ib_back_button -> {
                findNavController().navigateUp()
            }

            R.id.btn_update -> {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.edit_account))
                    .setMessage("Are you sure want to change your account data?")
                    .setIcon(R.drawable.password)
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel.changeAuthData()
                    }
                    .setNegativeButton("No") { _, _ ->
                        Snackbar.make(
                            binding.root,
                            "Canceled editing account data!",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    .create().show()
            }
        }
    }
}