package com.example.bitebyte.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "recipe")
@Parcelize
data class Recipe (
    val calories: Float,
    val carbohydrates: Float,
    val description: String,
    val fat: Float,
    @PrimaryKey
    val id: Int,
    val ingredients: List<String>,
    val minutes: Float,
    val name: String,
    val protein: Float,
    val saturated_fat: Float,
    val sodium: Float,
    val steps: List<String>,
    val sugar: Float,
    val tags: List<String>,
    val total_fat: Float,
    val vegetarian: Boolean,
    val images: String
) : Parcelable

data class RecipesResponse(
    val result: List<Recipe>,
)

@Entity(tableName = "shopping_list")
data class ShoppingList(
    @PrimaryKey
    val id: Int,
    val name: String,
    val ingredients: List<String>,
)
