package com.example.bitebyte.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.bitebyte.data.model.Recipe
import com.example.bitebyte.databinding.VerticalMenuItemLayoutBinding
import com.example.bitebyte.utils.RecipeDiffCallback

class VerticalRecipeAdapter(private val onClickStoryListener: OnClickRecipeListener) : RecyclerView.Adapter<VerticalRecipeAdapter.RecipeViewHolder>(){

    private var recipeList = ArrayList<Recipe>()


    fun clearRecipe() {
        recipeList.clear()
        notifyDataSetChanged()
    }

    fun bindRecipe(recipe: List<Recipe>) {
        val diffCallback = RecipeDiffCallback(recipeList, recipe)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        recipeList.clear()
        recipeList.addAll(recipe)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class RecipeViewHolder(private val binding: VerticalMenuItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.recipe = recipe
            val requestOption = RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()

            Glide.with(itemView.context)
                .load(recipe.images)
                .apply(requestOption)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivRecipe)
            binding.recipeContainer.setOnClickListener {
                onClickStoryListener.onClick(recipe, binding)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(VerticalMenuItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = recipeList.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    class OnClickRecipeListener(val clickListener: (recipe: Recipe, binding: VerticalMenuItemLayoutBinding) -> Unit) {
        fun onClick(recipe: Recipe, binding: VerticalMenuItemLayoutBinding) = clickListener(recipe, binding)
    }
}