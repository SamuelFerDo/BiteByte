package com.example.bitebyte.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bitebyte.data.model.Recipe
import com.example.bitebyte.data.model.ShoppingList
import com.example.bitebyte.repository.RecipeRepository

class DetailRecipeViewModel(application: Application) : ViewModel() {

    private val recipeRepository : RecipeRepository = RecipeRepository(application)

    fun getAllRecipe(): LiveData<List<Recipe>> {
        return recipeRepository.getAllFavorites()
    }

    fun insertFavoriteRecipe(recipe: Recipe) {
        recipeRepository.insert(recipe)
    }

    fun deleteFavoriteRecipe(id: Int){
        recipeRepository.delete(id)
    }

    fun getAllShoppingList() : LiveData<List<ShoppingList>> {
        return recipeRepository.getAllShoppingList()
    }

    fun insertShoppingList(shoppingList: ShoppingList) {
        recipeRepository.insertShoppingList(shoppingList)
    }
    fun deleteShoppingList(id: Int){
        recipeRepository.deleteShoppingList(id)
    }
}