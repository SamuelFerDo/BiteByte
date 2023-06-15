package com.example.bitebyte.ui.bottomnav.ui.profile

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bitebyte.R
import com.example.bitebyte.data.model.ApiResult
import com.example.bitebyte.data.model.ChangeAuthBody
import com.example.bitebyte.data.remote.ApiConfig
import com.example.bitebyte.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditAccountViewModel(application: Application, val sessionManager: SessionManager) : AndroidViewModel(application) {

    companion object{
        private const val TAG = "EditAccountViewModel"
    }

    private val apiService = ApiConfig.getApiService()
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val oldPassword = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>()

    private val _editAccount = MutableLiveData<ApiResult>()
    val editAccount : MutableLiveData<ApiResult> get() = _editAccount

    fun changeAuthData(){
        if(emptyField(name.value, email.value, oldPassword.value, newPassword.value)){
            if (oldPassword.value!!.count() >= 8 && newPassword.value!!.count() >= 8) {
                    _editAccount.postValue(ApiResult.Loading)
                    val changeAuthData = ChangeAuthBody(
                        name.value!!,
                        email.value!!,
                        oldPassword.value!!,
                        newPassword.value!!
                    )
                    viewModelScope.launch(Dispatchers.IO) {
                        val response =
                            apiService.changeAuthData(sessionManager.getToken()!!, changeAuthData, userId = sessionManager.getID()!!.toInt())

                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            Log.d(TAG, "Success: ${responseBody}")
                            _editAccount.postValue(ApiResult.Success)
                            sessionManager.updateName(name.value!!)
                        } else {
                            Log.e(TAG, "Error: ${response.message()}")
                            _editAccount.postValue(
                                ApiResult.Error(
                                    getApplication<Application>().getString(
                                        R.string.edit_account_failed,
                                        response.message()
                                    )
                                )
                            )
                        }
                    }
            }else{
                _editAccount.postValue(ApiResult.Error(getApplication<Application>().getString(R.string.password_less_then_8)))
            }
        } else {
            _editAccount.postValue(ApiResult.Error(getApplication<Application>().getString(R.string.fill_all_fields)))
        }
    }
}

    private fun emptyField(name: String?, email: String?, oldPassword: String?, newPassword: String?) : Boolean{
        return when{
            TextUtils.isEmpty(name) -> false
            TextUtils.isEmpty(email) -> false
            TextUtils.isEmpty(oldPassword) -> false
            TextUtils.isEmpty(newPassword) -> false
            else -> true
        }
    }