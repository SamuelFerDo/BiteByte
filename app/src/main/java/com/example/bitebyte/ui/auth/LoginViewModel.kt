package com.example.bitebyte.ui.auth

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bitebyte.R
import com.example.bitebyte.data.model.ApiResult
import com.example.bitebyte.data.model.LoginBody
import com.example.bitebyte.data.remote.ApiConfig
import com.example.bitebyte.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel (application: Application, private val seesionManager: SessionManager) : AndroidViewModel(application) {
    companion object{
        const val TAG = "LoginViewModel"
    }

    private val apiService = ApiConfig.getApiService()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _login = MutableLiveData<ApiResult>()
    val login : MutableLiveData<ApiResult> get() = _login

    fun login(){
        if(emptyField(email.value, password.value)) {
            if (password.value!!.count() >= 8) {
                _login.postValue(ApiResult.Loading)
                val loginBody = LoginBody(email.value!!, password.value!!)
                viewModelScope.launch(Dispatchers.IO) {
                    val response = apiService.login(loginBody)

                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        Log.d(TAG, "Success: ${responseBody!!}")
                        seesionManager.addUserSession(
                            responseBody.username,
                            response.body()!!.token,
                            response.body()!!.email,
                            response.body()!!.userId.toString(),
                        )
                        if(responseBody.age > 0) {
                            seesionManager.addFillInput("true")
                        }
                        seesionManager.addUserInput(
                            responseBody.age.toString(),
                            responseBody.gender.toString(),
                            responseBody.height.toString(),
                            responseBody.weight.toString(),
                            responseBody.health_concern.toString(),
                            responseBody.menu_type.toString(),
                            responseBody.activity_type.toString()
                        )
                        if(responseBody!!.images == null) {
                            seesionManager.changePhoto("")
                        } else{
                            seesionManager.changePhoto(responseBody.images)
                        }
                        _login.postValue(ApiResult.Success)
                    } else {
                        Log.e(TAG, "Error: ${response.message()}")
                        _login.postValue(
                            ApiResult.Error(
                                getApplication<Application>().getString(
                                    R.string.login_failed,
                                    response.message()
                                )
                            )
                        )
                    }
                }
            }else{
                _login.postValue(ApiResult.Error(getApplication<Application>().getString(R.string.password_less_then_8)))
            }
        }else {
            _login.postValue(ApiResult.Error(getApplication<Application>().getString(R.string.fill_all_fields)))
        }
    }

    private fun emptyField(email: String?, password: String?) : Boolean{
        return when{
            TextUtils.isEmpty(email) -> false
            TextUtils.isEmpty(password) -> false
            else -> true
        }
    }

}