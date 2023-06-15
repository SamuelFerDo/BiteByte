package com.example.bitebyte.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitebyte.data.model.ShoppingList
import com.example.bitebyte.databinding.ShoppingListItemLayoutBinding

class ShoppingListAdapter(private val shoppingList: ShoppingList):  ListAdapter<ShoppingList, ShoppingListAdapter.RecipeViewHolder>(DIFF_CALLBACK) {

    inner class RecipeViewHolder(private val binding: ShoppingListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: String) {
            binding.tvIngredientsName.text = recipe
            binding.tvRecipeName.text = shoppingList.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            ShoppingListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = shoppingList.ingredients.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = shoppingList.ingredients[position]
        holder.bind(recipe)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ShoppingList> =
            object : DiffUtil.ItemCallback<ShoppingList>() {
                override fun areItemsTheSame(
                    oldItem: ShoppingList,
                    newItem: ShoppingList
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: ShoppingList,
                    newItem: ShoppingList
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }
}