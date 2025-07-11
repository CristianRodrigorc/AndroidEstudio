package com.cristian.appgym.network

import com.cristian.appgym.data.model.LoginRequest
import com.cristian.appgym.data.model.RegisterRequest
import com.cristian.appgym.data.model.Usuario
import com.cristian.appgym.model.UserData
import com.cristian.appgym.model.UserDataRequest
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    // Endpoints básicos de usuario
    @GET("api/usuarios/{id}")
    suspend fun obtenerUsuario(@Path("id") id: Long): Response<Usuario>

    @GET("api/usuarios/data/{userId}")
    suspend fun obtenerUserData(@Path("userId") userId: Long): Response<UserData>

    @POST("api/usuarios/data/save")
    suspend fun guardarUserData(@Body userData: UserDataRequest): Response<Void>

    // Endpoints de verificación
    @GET("api/usuarios/email/{email}")
    suspend fun verificarEmail(@Path("email") email: String): Response<Usuario>

    @GET("api/usuarios/username/{username}")
    suspend fun verificarUsername(@Path("username") username: String): Response<Usuario>

    // Endpoints de usuario
    @POST("api/usuarios")
    suspend fun crearUsuario(@Body request: RegisterRequest): Response<Usuario>

    @GET("api/usuarios/email/{email}")
    suspend fun login(@Path("email") email: String): Response<Usuario>

    @GET("api/usuarios/username/{username}")
    suspend fun obtenerUsuarioPorUsername(@Path("username") username: String): Response<Usuario>

    @GET("api/usuarios/email/{email}")
    suspend fun obtenerUsuarioPorEmail(@Path("email") email: String): Response<Usuario>

    @GET("api/usuarios/data/user/{userId}")
    suspend fun obtenerDatosUsuario(@Path("userId") userId: Long): Response<Usuario>
}