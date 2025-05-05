package com.cristian.appgym.data

import com.cristian.appgym.data.model.LoginRequest
import com.cristian.appgym.data.model.LoginResponse
import com.cristian.appgym.data.model.RegisterRequest
import com.cristian.appgym.data.model.RegisterResponse
import com.cristian.appgym.data.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("api/usuarios/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/usuarios/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("api/usuarios/check-email/{email}")
    suspend fun checkEmail(@Path("email") email: String): Response<Boolean>

    @GET("api/usuarios/check-username/{username}")
    suspend fun checkUsername(@Path("username") username: String): Response<Boolean>
} 