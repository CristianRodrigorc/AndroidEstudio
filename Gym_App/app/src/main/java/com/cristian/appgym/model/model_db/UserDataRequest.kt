package com.cristian.appgym.model.model_db

data class UserDataRequest(
    val userId: Long?,
    val size: Double?,
    val weight: Double?,
    val physicalActivity: String?,
    val daysTraining: Int?,
    val healthProblems: String?,
    val preferenceSchedule: String?,
    val motivation: String?
) 