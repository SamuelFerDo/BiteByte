package com.example.bitebyte.data.remote

import com.example.bitebyte.data.model.ChangeAuthBody
import com.example.bitebyte.data.model.ChangeAuthResponse
import com.example.bitebyte.data.model.LoginBody
import com.example.bitebyte.data.model.LoginResponse
import com.example.bitebyte.data.model.PhotoUploadResponse
import com.example.bitebyte.data.model.RecipesResponse
import com.example.bitebyte.data.model.RegisterBody
import com.example.bitebyte.data.model.RegisterResponse
import com.example.bitebyte.data.model.UserBody
import com.example.bitebyte.data.model.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("register")
    suspend fun register(@Body body: RegisterBody): Response<RegisterResponse>

    @POST("login")
    suspend fun login(@Body body: LoginBody): Response<LoginResponse>

    @POST("addUsersIdentity")
    suspend fun addUserInformation(@Header("Authorization") token: String, @Body body: UserBody): Response<UserResponse>

    @PUT("usersIdentity/{userId}")
    suspend fun changeAuthData(@Header("Authorization") token: String, @Body body: ChangeAuthBody, @Path("userId") userId: Int? = null) : Response<ChangeAuthResponse>

    @GET("/{age}/{gender}/{height}/{weight}/{healthConcern}/{menuType}/{activity}")
    suspend fun getRecommendationRecipe(@Path("age") age: Int? = null,
                                        @Path("gender") gender: Int? = null,
                                        @Path("height") height: Int? = null,
                                        @Path("weight") weight: Int? = null,
                                        @Path("healthConcern") healthConcern: Int? = null,
                                        @Path("menuType") menuType: Int? = null,
                                        @Path("activity") activity: Int? = null): Response<RecipesResponse>

    @GET("getAllFoodData")
    suspend fun getAllFoodData() : Response<RecipesResponse>

    @GET("searchRecipeByName")
    suspend fun searchRecipeByName(@Query("name") name: String? = null) : Response<RecipesResponse>

    @Multipart
    @POST("uploadImage/{userId}")
    suspend fun uploadImage(@Header("Authorization") token: String, @Part image: MultipartBody.Part, @Path("userId") userId: Int? = null) : Response<PhotoUploadResponse>
}