package com.cristian.appgym.model

import com.google.gson.annotations.SerializedName

data class UserData(
    // ID del usuario al que pertenecen estos datos
    @SerializedName("userId")
    val userId: Long? = null,
    
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