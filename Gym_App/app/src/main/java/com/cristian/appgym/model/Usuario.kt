package com.cristian.appgym.model

import com.google.gson.annotations.SerializedName

data class Usuario(
    @SerializedName("id_user")
    val id_user: Long? = null,
    
    @SerializedName("userId")
    val userId: Long? = null,
    
    @SerializedName("username")
    val username: String,
    
    @SerializedName("password")
    val password: String,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("lastname")
    val lastname: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("date")
    val date: String,
    
    @SerializedName("sex")
    val sex: String,
    
    @SerializedName("userData")
    val userData: UserData? = null
)