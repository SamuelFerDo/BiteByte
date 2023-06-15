package com.example.bitebyte.ui.shoppingList

import android.app.AlertDialog
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bitebyte.R
import com.example.bitebyte.adapter.ShoppingListAdapter
import com.example.bitebyte.databinding.FragmentShoppingListBinding
import com.example.bitebyte.ui.bottomnav.ui.profile.ProfileFragmentDirections
import com.example.bitebyte.utils.viewModelsFactory
import com.google.android.material.snackbar.Snackbar


class ShoppingListFragment : Fragment() {

    private var _binding: FragmentShoppingListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShoppingListViewModel by viewModelsFactory {
        ShoppingListViewModel(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibBackButton.setOnClickListener{
            findNavController().navigate(ShoppingListFragmentDirections.actionShoppingListFragmentToProfileFragment())
        }

        viewModel.getAllShoppingList().observe(viewLifecycleOwner) { ingredients ->
            if(ingredients.isNotEmpty()){
            ingredients.forEach { shoppingList ->
                val adapter = ShoppingListAdapter(shoppingList)
                binding.rvShoppingList.apply {
                    setHasFixedSize(true)
                    this.adapter = adapter
                    binding.ivNoShoppingList.visibility = View.GONE
                    binding.tvSubheaderIllustration.visibility = View.GONE
                }
                }
                binding.btnClearAll.setOnClickListener{
                    AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.clear_all_shopping_list))
                        .setMessage("Are you sure want to clear all the shopping list?")
                        .setIcon(R.drawable.ic_delete_orange)
                        .setPositiveButton("Yes") { _, _ ->
                            viewModel.deleteAllShoppingList()
                            findNavController().navigate(R.id.shoppingListFragment)
                        }
                        .setNegativeButton("No") { _, _ ->
                            Snackbar.make(binding.root, "Canceled clear all shopping list!", Snackbar.LENGTH_SHORT).show()
                        }
                        .create().show()
                }
            }else{
                binding.ivNoShoppingList.visibility = View.VISIBLE
                binding.tvSubheaderIllustration.visibility = View.VISIBLE
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
