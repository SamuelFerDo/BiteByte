package com.example.bitebyte.data.model

data class RegisterResponse(
    val error: Boolean,
    val message: String
)

data class LoginResponse(
    val username: String,
    val error: Boolean,
    val message: String,
    val token: String
)