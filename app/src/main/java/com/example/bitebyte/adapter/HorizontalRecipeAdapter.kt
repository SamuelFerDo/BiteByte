package com.example.bitebyte.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitebyte.data.model.Recipe
import com.example.bitebyte.databinding.HorizontalMenuItemLayoutBinding
import com.example.bitebyte.utils.RecipeDiffCallback

class HorizontalRecipeAdapter (private val onClickStoryListener: OnClickRecipeListener) : RecyclerView.Adapter<HorizontalRecipeAdapter.RecipeViewHolder>(){

    private var recipeList = ArrayList<Recipe>()

    fun bindRecipe(recipe: List<Recipe>) {
        val diffCallback = RecipeDiffCallback(recipeList, recipe)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        recipeList.clear()
        recipeList.addAll(recipe)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class RecipeViewHolder(private val binding: HorizontalMenuItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.recipe = recipe
            Glide.with(itemView.context)
                .load(recipe.images)
                .into(binding.ivRecipe)
            binding.recipeContainer.setOnClickListener {
                onClickStoryListener.onClick(recipe, binding)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(HorizontalMenuItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    class OnClickRecipeListener(val clickListener: (recipe: Recipe, binding: HorizontalMenuItemLayoutBinding) -> Unit) {
        fun onClick(recipe: Recipe, binding: HorizontalMenuItemLayoutBinding) = clickListener(recipe, binding)
    }
}