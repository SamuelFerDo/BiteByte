package com.example.bitebyte.ui.detail

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bitebyte.R
import com.example.bitebyte.adapter.RecipeDetailAdapter
import com.example.bitebyte.data.model.Recipe
import com.example.bitebyte.data.model.ShoppingList
import com.example.bitebyte.databinding.FragmentDetailRecipeBinding
import com.example.bitebyte.di.Injection
import com.example.bitebyte.ui.bottomnav.ui.recommendation.RecommendationViewModel
import com.example.bitebyte.utils.viewModelsFactory
import kotlinx.coroutines.launch


class DetailRecipeFragment : Fragment() {

    private val args: DetailRecipeFragmentArgs by navArgs()

    private var _binding : FragmentDetailRecipeBinding? = null
    private val binding get() = _binding!!

    private var ivFavorite: Boolean = false
    private var recipeFavorite: Recipe? = null

    private var isShopList : Boolean = false
    private var ingredientShopList : ShoppingList? = null

    private val viewModel: DetailRecipeViewModel by viewModelsFactory {
        DetailRecipeViewModel(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        _binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipe = args.recipe
        binding.apply {
            tvRecipeName.text = recipe.name

           recipe.images.apply {
                Glide.with(this@DetailRecipeFragment)
                    .load(this)
                    .into(binding.ivRecipeImage)
            }
            tvRecipeDescription.text = recipe.description
            tvCalories.text = recipe.calories.toString() + " Calories"
            tvMinutes.text = recipe.minutes.toString() + " Minutes"
            tvProtein.text = recipe.protein.toString() + " g"
            tvCarbo.text = recipe.carbohydrates.toString() + " g"
            tvSugar.text = recipe.sugar.toString() + " g"
            tvFat.text = recipe.fat.toString() + " g"
            tvSodium.text = recipe.sodium.toString() + " g"
            binding.rvSteps.apply {
                adapter = RecipeDetailAdapter(recipe.steps)
            }

            binding.rvIngredients.apply {
                adapter = RecipeDetailAdapter(recipe.ingredients)
            }

            binding.ibBackButton.setOnClickListener{
                findNavController().navigateUp()
            }
        }
            ingredientShopList = ShoppingList(
                recipe.id,
                recipe.name,
                recipe.ingredients,
            )
        viewModel.getAllShoppingList().observe(viewLifecycleOwner) { shoppingList ->
                if (shoppingList != null) {
                for (data in shoppingList) {
                    if (recipe.id == data.id) {
                        isShopList = true
                        binding.ivShoppingList.setImageResource(R.drawable.ic_delete_orange)
                        binding.tvShoppingList.text = getString(R.string.delete_shopping_list)
                    }
                }
            }
            binding.containerShoppingList.setOnClickListener{
                if (!isShopList) {
                    isShopList = true
                    binding.ivShoppingList.setImageResource(R.drawable.ic_delete_orange)
                    binding.tvShoppingList.text = getString(R.string.delete_shopping_list)
                    viewModel.insertShoppingList(ingredientShopList!!)
                    Toast.makeText(
                        requireContext(),
                        "Added to Shopping List",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    isShopList = false
                    binding.ivShoppingList.setImageResource(R.drawable.baseline_add_24)
                    binding.tvShoppingList.text = getString(R.string.add_to_shopping_list)
                    viewModel.deleteShoppingList(recipe.id)
                    Toast.makeText(
                        requireContext(),
                        "Removed from Shopping List",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
            recipeFavorite = Recipe(
                recipe.calories,
                recipe.carbohydrates,
                recipe.description,
                recipe.fat,
                recipe.id,
                recipe.ingredients,
                recipe.minutes,
                recipe.name,
                recipe.protein,
                recipe.saturated_fat,
                recipe.sodium,
                recipe.steps,
                recipe.sugar,
                recipe.tags,
                recipe.total_fat,
                recipe.vegetarian,
                recipe.images
            )
            viewModel.getAllRecipe().observe(viewLifecycleOwner) { recipeFavorites ->
                if (recipeFavorites != null) {
                    for (data in recipeFavorites) {
                        if (recipe.id == data.id) {
                            ivFavorite = true
                            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_full)
                        }
                }
            }
                binding.btnFavorite.setOnClickListener {
                    if (!ivFavorite) {
                        ivFavorite = true
                        binding.btnFavorite.setImageResource(R.drawable.ic_favorite_full)
                            viewModel.insertFavoriteRecipe(recipeFavorite!!)
                            Toast.makeText(
                                requireContext(),
                                "Added to Favorite",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                    } else {
                        ivFavorite = false
                        binding.btnFavorite.setImageResource(R.drawable.ic_favorite_border)
                            viewModel.deleteFavoriteRecipe(recipe.id)
                            Toast.makeText(requireContext(), "Favorite Deleted", Toast.LENGTH_SHORT)
                                .show()
                        }
                }
        }
    }
}