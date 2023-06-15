package com.example.bitebyte.ui.bottomnav.ui.home

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitebyte.R
import com.example.bitebyte.data.model.RecipesResponse
import com.example.bitebyte.data.model.ShoppingList
import com.example.bitebyte.data.remote.ApiConfig
import com.example.bitebyte.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    companion object{
        private const val TAG = "HomeViewModel"
    }
    private val apiService = ApiConfig.getApiService()

    private val _recipes = MutableLiveData<RecipesResponse>()
    val recipes: MutableLiveData<RecipesResponse> get() = _recipes

    private val _recipeSearch = MutableLiveData<RecipesResponse>()
    val recipeSearch: MutableLiveData<RecipesResponse> get() = _recipeSearch

    private val _loading = MutableLiveData(false)
    val loading: MutableLiveData<Boolean>
        get() = _loading

    init{
        getDatabaseRecipe()
    }

    fun getDatabaseRecipe(){
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.getAllFoodData()
            if (response.isSuccessful) {
                response.body()?.let {
                    _recipes.postValue(it)
                }
            } else {
                Log.e(TAG, "getDatabaseRecipe: Error ${response.errorBody()}")
            }
            _loading.postValue(false)
        }
    }

    fun searchRecipe(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.searchRecipeByName(query)
            if (response.isSuccessful) {
                response.body()?.let {
                    _recipeSearch.postValue(it)
                }
            } else {
                Log.e(TAG, "SearchRecipe: Error ${response.errorBody()}")
            }
        }
    }
}