package com.cristian.appgym.data.model

data class RegisterRequest(
    val nombre: String,
    val apellido: String,
    val email: String,
    val username: String,
    val password: String
) 