package com.example.bitebyte.data.remote

import com.example.bitebyte.data.model.LoginBody
import com.example.bitebyte.data.model.LoginResponse
import com.example.bitebyte.data.model.RegisterBody
import com.example.bitebyte.data.model.RegisterResponse
import com.example.bitebyte.data.model.UserBody
import com.example.bitebyte.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun register(@Body body: RegisterBody): Response<RegisterResponse>

    @POST("login")
    suspend fun login(@Body body: LoginBody): Response<LoginResponse>

    @POST("addUserInformation")
    suspend fun addUserInformation(@Header("Authorization") token: String, @Body body: UserBody): Response<UserResponse>
}