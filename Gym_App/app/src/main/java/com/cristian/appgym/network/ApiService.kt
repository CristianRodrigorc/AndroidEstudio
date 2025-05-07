package com.cristian.appgym.network

import com.cristian.appgym.model.UserData
import com.cristian.appgym.model.UserDataRequest
import com.cristian.appgym.model.Usuario
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
    @GET("api/usuarios/check-email/{email}")
    suspend fun verificarEmail(@Path("email") email: String): Response<Boolean>

    @GET("api/usuarios/check-username/{username}")
    suspend fun verificarUsername(@Path("username") username: String): Response<Boolean>

    // Endpoints de usuario
    @POST("api/usuarios/register")
    suspend fun crearUsuario(@Body usuario: Usuario): Response<Usuario>

    @POST("api/usuarios/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<Usuario>

    @GET("api/usuarios/findByUsername/{username}")
    suspend fun obtenerUsuarioPorUsername(@Path("username") username: String): Response<Usuario>

    @GET("api/usuarios/email/{email}")
    suspend fun obtenerUsuarioPorEmail(@Path("email") email: String): Response<Usuario>

    @GET("api/usuarios/data/user/{userId}")
    suspend fun obtenerDatosUsuario(@Path("userId") userId: Long): Response<Usuario>
}

data class LoginRequest(
    val username: String,
    val password: String
)