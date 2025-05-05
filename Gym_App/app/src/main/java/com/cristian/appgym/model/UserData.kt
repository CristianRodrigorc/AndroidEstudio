package com.cristian.appgym.model

import com.google.gson.annotations.SerializedName

data class UserData(
    // Campos básicos del usuario
    @SerializedName("id_user")
    val id: Long? = null,
    
    @SerializedName("userId")
    val userId: Long? = null,
    
    @SerializedName("username")
    val username: String,
    
    @SerializedName("password")
    val password: String,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("lastname")
    val lastName: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("date")
    val date: String,
    
    @SerializedName("sex")
    val sex: String,
    
    // Campos adicionales para datos del usuario
    @SerializedName("size")
    val size: Double? = null,
    
    @SerializedName("weight")
    val weight: Double? = null,
    
    @SerializedName("actividadFisica")
    val physicalActivity: String? = null,
    
    @SerializedName("diasEntrenar")
    val daysTraining: Int? = null,
    
    @SerializedName("problemasSalud")
    val healthProblems: String? = null,
    
    @SerializedName("preferenciaHorario")
    val preferenceSchedule: String? = null,
    
    @SerializedName("motivacion")
    val motivation: String? = null
)