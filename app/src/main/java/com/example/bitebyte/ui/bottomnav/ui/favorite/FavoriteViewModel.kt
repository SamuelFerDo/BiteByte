package com.example.bitebyte.ui.bottomnav.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bitebyte.data.model.Recipe
import com.example.bitebyte.repository.RecipeRepository

class FavoriteViewModel(application: Application) : ViewModel() {

    private val recipeRepository : RecipeRepository = RecipeRepository(application)
    fun getAllRecipe(): LiveData<List<Recipe>> {
        return recipeRepository.getAllFavorites()
    }

    fun deleteFavoriteRecipe(id: Int){
        recipeRepository.delete(id)
    }
}