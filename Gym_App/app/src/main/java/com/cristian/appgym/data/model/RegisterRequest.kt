package com.cristian.appgym.data.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("nombre")
    val nombre: String,
    
    @SerializedName("apellidos")
    val apellidos: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("username")
    val username: String,
    
    @SerializedName("password")
    val password: String,
    
    @SerializedName("fechaNacimiento")
    val fechaNacimiento: String? = null,
    
    @SerializedName("sexo")
    val sexo: String? = null
) 