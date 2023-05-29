package com.example.bitebyte.data.model

data class RegisterBody(
    val username: String,
    val email: String,
    val password: String
)

data class LoginBody(
    val email: String,
    val password: String
)
