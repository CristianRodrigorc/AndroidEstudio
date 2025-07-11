package com.cristian.appgym.network

import com.cristian.appgym.model.model_db.RegisterRequest
import com.cristian.appgym.model.model_db.Usuario
import com.cristian.appgym.model.model_db.UserData
import com.cristian.appgym.model.model_db.UserDataRequest
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    // ===== ENDPOINTS DE AUTENTICACIÓN =====
    
    // Login por username
    @GET("api/usuarios/username/{username}")
    suspend fun loginByUsername(@Path("username") username: String): Response<Usuario>
    
    // Login por email
    @GET("api/usuarios/email/{email}")
    suspend fun loginByEmail(@Path("email") email: String): Response<Usuario>
    
    // Registro de usuario
    @POST("api/usuarios")
    suspend fun register(@Body request: RegisterRequest): Response<Usuario>




    
    
    // ===== ENDPOINTS DE VERIFICACIÓN =====
    
    // Verificar si email existe
    @GET("api/usuarios/email/{email}")
    suspend fun checkEmail(@Path("email") email: String): Response<Usuario>
    
    // Verificar si username existe
    @GET("api/usuarios/username/{username}")
    suspend fun checkUsername(@Path("username") username: String): Response<Usuario>
    
    // ===== ENDPOINTS DE USUARIO =====
    
    // Obtener usuario por ID
    @GET("api/usuarios/{id}")
    suspend fun getUserById(@Path("id") id: Long): Response<Usuario>
    
    // Obtener usuario por username
    @GET("api/usuarios/username/{username}")
    suspend fun getUserByUsername(@Path("username") username: String): Response<Usuario>
    
    // Obtener usuario por email
    @GET("api/usuarios/email/{email}")
    suspend fun getUserByEmail(@Path("email") email: String): Response<Usuario>
    
    // ===== ENDPOINTS DE DATOS ADICIONALES =====
    
    // Obtener datos adicionales del usuario
    @GET("api/usuarios/data/{userId}")
    suspend fun getUserData(@Path("userId") userId: Long): Response<UserData>
    
    // Guardar datos adicionales del usuario
    @POST("api/usuarios/data/save")
    suspend fun saveUserData(@Body userData: UserDataRequest): Response<Void>
}