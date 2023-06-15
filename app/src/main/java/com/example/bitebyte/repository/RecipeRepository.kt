package com.example.bitebyte.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.bitebyte.data.local.RecipeDao
import com.example.bitebyte.data.local.RecipeDatabase
import com.example.bitebyte.data.local.ShoppingListDao
import com.example.bitebyte.data.model.Recipe
import com.example.bitebyte.data.model.ShoppingList
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RecipeRepository(application: Application) {
    private val mRecipeDao: RecipeDao
    private val mShoppingListDao : ShoppingListDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = RecipeDatabase.getDatabase(application)
        mRecipeDao = db.recipeDao()
        mShoppingListDao = db.shoppingListDao()
    }
    fun getAllFavorites(): LiveData<List<Recipe>> = mRecipeDao.getAllRecipe()

    fun insert(recipe: Recipe) {
        executorService.execute { mRecipeDao.insertFavorite(recipe) }
    }

    fun delete(id: Int) {
        executorService.execute { mRecipeDao.removeFavorite(id) }
    }

    fun getAllShoppingList() : LiveData<List<ShoppingList>> = mShoppingListDao.getAllShoppingList()

    fun insertShoppingList(shoppingList: ShoppingList) {
        executorService.execute { mShoppingListDao.insertShoppingList(shoppingList) }
    }

    fun deleteShoppingList(id: Int) {
        executorService.execute { mShoppingListDao.removeShoppingList(id) }
    }

    fun deleteAllShoppingList() {
        executorService.execute { mShoppingListDao.removeAllShoppingList() }
    }
}