package com.example.bitebyte.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitebyte.data.model.Recipe
import com.example.bitebyte.databinding.FavoriteMenuItemLayoutBinding
import com.example.bitebyte.utils.RecipeDiffCallback

class FavoriteRecipeAdapter(private val onClickRecipeListener: OnClickRecipeListener, private val onUnfavoriteClickListener: OnUnfavoriteClickListener) : RecyclerView.Adapter<FavoriteRecipeAdapter.RecipeViewHolder>(){

    private var recipeList = ArrayList<Recipe>()

    fun bindRecipe(recipe: List<Recipe>) {
        val diffCallback = RecipeDiffCallback(recipeList, recipe)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        recipeList.clear()
        recipeList.addAll(recipe)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class RecipeViewHolder(private val binding: FavoriteMenuItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.recipe = recipe
            Glide.with(itemView.context)
                .load(recipe.images)
                .into(binding.ivRecipe)
            binding.recipeContainer.setOnClickListener {
                onClickRecipeListener.onClick(recipe, binding)
            }
            binding.btnFavorite.setOnClickListener {
                onUnfavoriteClickListener.onUnfavoriteClick(recipe)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(FavoriteMenuItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    interface OnUnfavoriteClickListener {
        fun onUnfavoriteClick(recipe: Recipe)
    }

    class OnClickRecipeListener(val clickListener: (recipe: Recipe, binding: FavoriteMenuItemLayoutBinding) -> Unit) {
        fun onClick(recipe: Recipe, binding: FavoriteMenuItemLayoutBinding) = clickListener(recipe, binding)
    }
}