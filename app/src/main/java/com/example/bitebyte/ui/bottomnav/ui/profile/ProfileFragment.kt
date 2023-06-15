package com.example.bitebyte.ui.bottomnav.ui.profile

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.bitebyte.R
import com.example.bitebyte.adapter.ShoppingListAdapter
import com.example.bitebyte.data.model.ApiResult
import com.example.bitebyte.databinding.FragmentProfileBinding
import com.example.bitebyte.ui.bottomnav.ui.profile.camera.CameraActivity
import com.example.bitebyte.utils.SessionManager
import com.example.bitebyte.utils.reduceFileImage
import com.example.bitebyte.utils.rotateFile
import com.example.bitebyte.utils.uriToFile
import com.example.bitebyte.utils.viewModelsFactory
import com.google.android.material.snackbar.Snackbar
import java.io.File


class ProfileFragment : Fragment() {

    private val sessionManager: SessionManager by lazy {
        SessionManager(requireContext())
    }

    private val viewModel: ProfileViewModel by viewModelsFactory {
        ProfileViewModel(requireActivity().application,sessionManager)
    }

    private var getFile: File? = null

    private lateinit var shoppingListAdapter: ShoppingListAdapter

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        if (it.containsValue(false)) {
            Toast.makeText(requireContext(), getString(R.string.camera_permission_required), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun allPermissionGranted() = REQUIRED_PERMISSION.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra(PICTURE, File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as File?

            val isBackCamera = it.data?.getBooleanExtra(IS_BACK_CAMERA, true) as Boolean

            myFile?.let { file ->
                rotateFile(
                    file,
                    isBackCamera
                )
                getFile = file
                binding.ciProfile.setImageBitmap(BitmapFactory.decodeFile(file.path))
                if (getFile != null) {
                    val file = reduceFileImage(getFile as File)
                    viewModel.uploadPhoto(file)
                }
            }
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, requireContext())
                getFile = myFile
                binding.ciProfile.setImageURI(uri)
                if (getFile != null) {
                    val file = reduceFileImage(getFile as File)
                    viewModel.uploadPhoto(file)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!allPermissionGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        viewModel.getAllShoppingList().observe(viewLifecycleOwner) { ingredients ->
            ingredients.forEach { shoppingList ->
                shoppingListAdapter = ShoppingListAdapter(shoppingList)
                binding.tvTotalItemCount.text = shoppingListAdapter.itemCount.toString()
                binding.tvUnpurchasedCount.text = shoppingListAdapter.itemCount.toString()
            }
        }

        binding.tvName.text = sessionManager.getName()
        binding.btnPremium.setOnClickListener{
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToPremiumFragment())
        }

        binding.cvLogout.setOnClickListener {
            sessionManager.clearSession()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
            Snackbar.make(binding.root, getString(R.string.logged_out), Snackbar.LENGTH_SHORT).show()
        }
        binding.cvEditPersonalData.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.edit_personal_data))
                .setMessage("Are you sure want to change your personal data?")
                .setIcon(R.drawable.edit_personal_data)
                .setPositiveButton("Yes") { _, _ ->
                    sessionManager.resetFillInput()
                    findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToQuestionStartFragment())
                }
                .setNegativeButton("No") { _, _ ->
                    Snackbar.make(binding.root, "Canceled editing personal data!", Snackbar.LENGTH_SHORT).show()
                }
                .create().show()
        }

        binding.cvShoppingList.setOnClickListener{
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToShoppingListFragment())
        }

        binding.ivShoppingListArrow.setOnClickListener{
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToShoppingListFragment())
        }

        binding.cvEditAccount.setOnClickListener{
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToEditAccountFragment())
        }

        binding.ciProfile.setOnClickListener{
            startCameraX()
        }
        binding.btnGallery.setOnClickListener{
            startGallery()
        }
        if(sessionManager.getUserPhoto() != ""){
            binding.ciProfile.apply {
                Glide.with(this@ProfileFragment)
                    .load(sessionManager.getUserPhoto())
                    .into(binding.ciProfile)
            }
        }

        viewModel.uploadPhoto.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Success -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.success_changin_photo), Toast.LENGTH_SHORT
                    ).show()
                }

                is ApiResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.upload_image_error, it.error),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.changing_photo), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, getString(R.string.choose_picture))
        launcherIntentGallery.launch(chooser)
    }

    private fun startCameraX() {
        val intent = Intent(requireActivity(), CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }
    companion object{
        const val CAMERA_X_RESULT = 200
        const val IS_BACK_CAMERA = "isBackCamera"
        const val PICTURE = "picture"

        private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    }
}