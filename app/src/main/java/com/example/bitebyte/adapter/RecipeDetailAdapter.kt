package com.example.bitebyte.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitebyte.databinding.RecipeDetailItemBinding

class RecipeDetailAdapter(private val list: List<String>): RecyclerView.Adapter<RecipeDetailAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(private val binding: RecipeDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: String) {
            binding.tvRecipe.text = recipe
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            RecipeDetailItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = list[position]
        holder.bind(recipe)
    }
}