package dev.oure.workoutlog.ui

import dev.oure.workoutlog.models.LoginRequest
import dev.oure.workoutlog.models.LoginResponse
import dev.oure.workoutlog.models.RegisterRequest
import dev.oure.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}