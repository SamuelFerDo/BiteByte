package com.example.bitebyte.ui.question.generate

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bitebyte.R
import com.example.bitebyte.data.model.ApiResult
import com.example.bitebyte.data.model.UserBody
import com.example.bitebyte.data.remote.ApiConfig
import com.example.bitebyte.ui.auth.LoginViewModel
import com.example.bitebyte.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenerateViewModel(application: Application,val sessionManager: SessionManager, age:String, weight:String, height:String, gender:String, healthConcern: String, menuType: String, activity: String) : AndroidViewModel(application) {
    companion object{
        const val TAG = "GenerateViewModel"
    }

    val menuType = menuType
    val healthConcern = healthConcern
    val age = age
    val weight = weight
    val height = height
    val gender = gender
    val activity = activity

    private val apiService = ApiConfig.getApiService()

    private val _userData = MutableLiveData<ApiResult>()
    val userData : MutableLiveData<ApiResult> get() = _userData

    fun postUserData() {
        val userBody = UserBody(
            age = age.toInt(),
            weight = weight.toInt(),
            height = height.toInt(),
            gender = gender.toInt(),
            health_concern = healthConcern.toInt(),
            menu_type = menuType.toInt(),
            activity_type = activity.toInt()
        )
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.addUserInformation(sessionManager.getToken()!!,userBody)

            if (response.isSuccessful) {
                val responseBody = response.body()
                Log.d(TAG, "Success: ${responseBody}")
                _userData.postValue(ApiResult.Success)
                sessionManager.addUserInput(
                    age,
                    gender,
                    height,
                    weight,
                    healthConcern,
                    menuType,
                    activity)
            } else {
                Log.e(TAG, "Error: ${response.message()}")
                _userData.postValue(
                    ApiResult.Error(
                        getApplication<Application>().getString(
                            R.string.generating_failed,
                            response.message()
                        )
                    )
                )
            }
        }
    }
}