package com.example.bitebyte.ui.bottomnav.ui.favorite

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bitebyte.R
import com.example.bitebyte.adapter.FavoriteRecipeAdapter
import com.example.bitebyte.data.model.Recipe
import com.example.bitebyte.databinding.FragmentFavoriteBinding
import com.example.bitebyte.utils.viewModelsFactory

class FavoriteFragment : Fragment(), FavoriteRecipeAdapter.OnUnfavoriteClickListener {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModelsFactory {
        FavoriteViewModel(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val adapter = FavoriteRecipeAdapter(
            FavoriteRecipeAdapter.OnClickRecipeListener { recipe, binding ->
                findNavController().navigate(
                    FavoriteFragmentDirections.actionFavoriteFragmentToDetailRecipeFragment(
                        recipe
                    )
                )
            }, this
       )
            viewModel.getAllRecipe().observe(viewLifecycleOwner) { recipeList ->
                if (recipeList.isNotEmpty()){
                    adapter.bindRecipe(recipeList)
                    binding.ivNoFavorite.visibility = View.GONE
                    binding.tvSubheaderIllustration.visibility = View.GONE
                }else {
                    adapter.bindRecipe(recipeList)
                    binding.ivNoFavorite.visibility = View.VISIBLE
                    binding.tvSubheaderIllustration.visibility = View.VISIBLE
                }
                binding.rvFavoriteRecipe.apply {
                    setHasFixedSize(true)
                    this.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onUnfavoriteClick(recipe: Recipe) {
        viewModel.deleteFavoriteRecipe(recipe.id)
        Toast.makeText(requireContext(), "Favorite Deleted", Toast.LENGTH_SHORT)
            .show()
    }
}