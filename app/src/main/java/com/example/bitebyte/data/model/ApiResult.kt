package com.example.bitebyte.data.model

sealed class ApiResult {
    object Success : ApiResult()
    data class Error(val error: String) : ApiResult()
    object Loading : ApiResult()
}