package com.example.bitebyte.ui.bottomnav.ui.recommendation


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitebyte.data.model.RecipesResponse
import com.example.bitebyte.data.remote.ApiConfig
import com.example.bitebyte.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecommendationViewModel(private val sessionManager: SessionManager) : ViewModel() {

    companion object{
        private const val TAG = "RecommendationViewModel"
    }

    private val apiService = ApiConfig.getApiService()

    private val _recipes = MutableLiveData<RecipesResponse>()
    val recipes: MutableLiveData<RecipesResponse> get() = _recipes

    private val _loading = MutableLiveData(false)
    val loading: MutableLiveData<Boolean>
        get() = _loading

    init{
        getRecommendationRecipe()
    }
    fun getRecommendationRecipe(){
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.getRecommendationRecipe(sessionManager.getAge()?.toInt(), sessionManager.getGender()?.toInt(),sessionManager.getHeight()?.toInt(),sessionManager.getWeight()?.toInt(),sessionManager.getHealthConcern()?.toInt(),sessionManager.getMenuType()?.toInt(),sessionManager.getActivityType()?.toInt())
            if (response.isSuccessful) {
                response.body()?.let {
                    _recipes.postValue(it)
                }
            } else {
                Log.e(TAG, "getRecommendationRecipe: Error ${response.errorBody()}")
            }
            _loading.postValue(false)
        }
    }
}