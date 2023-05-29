package com.example.bitebyte.ui.auth

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bitebyte.R
import com.example.bitebyte.data.model.ApiResult
import com.example.bitebyte.data.model.RegisterBody
import com.example.bitebyte.data.remote.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    companion object{
        private const val TAG = "RegisterViewModel"
    }

    private val apiService = ApiConfig.getApiService()
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _register = MutableLiveData<ApiResult>()
    val register : MutableLiveData<ApiResult> get() = _register

    fun register(){
        if(emptyField(name.value, email.value, password.value)){
            if (password.value!!.count() >= 8) {
                _register.postValue(ApiResult.Loading)
                val registerData = RegisterBody(name.value!!, email.value!!, password.value!!)
                viewModelScope.launch(Dispatchers.IO) {
                    val response = apiService.register(registerData)

                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        Log.d(TAG, "Success: ${responseBody}")
                        _register.postValue(ApiResult.Success)
                    } else {
                        Log.e(TAG, "Error: ${response.message()}")
                        _register.postValue(
                            ApiResult.Error(
                                getApplication<Application>().getString(
                                    R.string.register_failed,
                                    response.message()
                                )
                            )
                        )
                    }
                }
            }else{
                _register.postValue(ApiResult.Error(getApplication<Application>().getString(R.string.password_less_then_8)))
            }
        } else {
            _register.postValue(ApiResult.Error(getApplication<Application>().getString(R.string.fill_all_fields)))
        }
    }

    private fun emptyField(name: String?, email: String?, password: String?) : Boolean{
        return when{
            TextUtils.isEmpty(name) -> false
            TextUtils.isEmpty(email) -> false
            TextUtils.isEmpty(password) -> false
            else -> true
        }
    }
}