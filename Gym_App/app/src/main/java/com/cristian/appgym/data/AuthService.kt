package com.cristian.appgym.data

import com.cristian.appgym.data.model.LoginRequest
import com.cristian.appgym.data.model.LoginResponse
import com.cristian.appgym.data.model.RegisterRequest
import com.cristian.appgym.data.model.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    // Login - el backend actual no tiene endpoint específico de login, usaremos buscar por email
    @GET("api/usuarios/email/{email}")
    suspend fun login(@Path("email") email: String): Response<Usuario>

    // Registro
    @POST("api/usuarios")
    suspend fun register(@Body request: RegisterRequest): Response<Usuario>

    // Verificar email
    @GET("api/usuarios/email/{email}")
    suspend fun checkEmail(@Path("email") email: String): Response<Usuario>

    // Verificar username
    @GET("api/usuarios/username/{username}")
    suspend fun checkUsername(@Path("username") username: String): Response<Usuario>
} 