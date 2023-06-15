package com.example.bitebyte.ui.shoppingList

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitebyte.data.model.Recipe
import com.example.bitebyte.data.model.ShoppingList
import com.example.bitebyte.repository.RecipeRepository
import kotlinx.coroutines.launch

class ShoppingListViewModel(application: Application) : ViewModel() {

        private val recipeRepository : RecipeRepository = RecipeRepository(application)
        fun getAllShoppingList(): LiveData<List<ShoppingList>> {
            return recipeRepository.getAllShoppingList()
        }

    fun deleteAllShoppingList() {
            recipeRepository.deleteAllShoppingList()
    }
}