package com.cristian.appgym.model.model_db

import com.google.gson.annotations.SerializedName

data class UserData(
    // ID del usuario al que pertenecen estos datos
    @SerializedName("userId")
    val userId: Long? = null,
    
    // Datos físicos del usuario
    @SerializedName("size")
    val size: Double? = null,
    
    @SerializedName("weight")
    val weight: Double? = null,
    
    // Datos de entrenamiento
    @SerializedName("actividadFisica")
    val physicalActivity: String? = null,
    
    @SerializedName("diasEntrenar")
    val daysTraining: Int? = null,
    
    @SerializedName("preferenciaHorario")
    val preferenceSchedule: String? = null,
    
    // Datos de salud
    @SerializedName("problemasSalud")
    val healthProblems: String? = null,
    
    @SerializedName("motivacion")
    val motivation: String? = null
) 