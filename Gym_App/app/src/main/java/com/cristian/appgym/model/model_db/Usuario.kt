package com.cristian.appgym.model.model_db

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Usuario(
    @SerializedName("id")
    val id: Long? = null,
    
    @SerializedName("nombre")
    val nombre: String,
    
    @SerializedName("apellidos")
    val apellidos: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("username")
    val username: String,
    
    @SerializedName("password")
    val password: String? = null,
    
    @SerializedName("fechaNacimiento")
    val fechaNacimiento: String? = null,
    
    @SerializedName("sexo")
    val sexo: String? = null
) 