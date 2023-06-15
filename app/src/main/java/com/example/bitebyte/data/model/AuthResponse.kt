package com.example.bitebyte.data.model

data class RegisterResponse(
    val error: String,
    val message: String
)

data class LoginResponse(
    val username: String,
    val error: String,
    val message: String,
    val token: String,
    val email: String,
    val userId: Int,
    val activity_type: Int,
    val age: Int,
    val gender : Int,
    val health_concern: Int,
    val height: Int,
    val menu_type: Int,
    val weight: Int,
    val images: String,
)

data class ChangeAuthResponse(
    val error: String,
    val message: String,
    val username: String,
    val email: String
)