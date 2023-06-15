package com.example.bitebyte.ui.bottomnav.ui.profile

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitebyte.data.model.ApiResult
import com.example.bitebyte.data.model.ShoppingList
import com.example.bitebyte.data.remote.ApiConfig
import com.example.bitebyte.repository.RecipeRepository
import com.example.bitebyte.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProfileViewModel(application: Application, val sessionManager: SessionManager) : ViewModel() {
    companion object{
        const val TAG = "ProfileViewModel"
    }
    private val apiService = ApiConfig.getApiService()

    private val _uploadPhoto = MutableLiveData<ApiResult>()
    val uploadPhoto: LiveData<ApiResult>
        get() = _uploadPhoto


    private val recipeRepository : RecipeRepository = RecipeRepository(application)
    fun getAllShoppingList(): LiveData<List<ShoppingList>> {
        return recipeRepository.getAllShoppingList()
    }

    fun uploadPhoto(file: File) {
        val authToken = sessionManager.getToken()
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
        val imageMultiPart = MultipartBody.Part.createFormData(
            "image",
            file.name,
            requestImageFile
        )
        viewModelScope.launch(Dispatchers.IO) {
            val uploadImageResponse =
                apiService.uploadImage(authToken!!, imageMultiPart, sessionManager.getID()!!.toInt())

            if (uploadImageResponse.isSuccessful) {
                _uploadPhoto.postValue(ApiResult.Success)
                sessionManager.changePhoto(uploadImageResponse.body()!!.imageUrl)
            } else {
                _uploadPhoto.postValue(ApiResult.Error(uploadImageResponse.message()))
            }
        }
    }
}