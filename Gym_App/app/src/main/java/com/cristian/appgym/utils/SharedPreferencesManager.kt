package com.cristian.appgym.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // ID del usuario
    fun getUserId(): Int {
        return sharedPreferences.getInt(KEY_USER_ID, -1)
    }

    fun setUserId(userId: Int) {
        sharedPreferences.edit().putInt(KEY_USER_ID, userId).apply()
    }

    // Datos básicos
    fun getUserName(): String? {
        return sharedPreferences.getString(KEY_USER_NAME, null)
    }

    fun setUserName(name: String) {
        sharedPreferences.edit().putString(KEY_USER_NAME, name).apply()
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString(KEY_USER_EMAIL, null)
    }

    fun setUserEmail(email: String) {
        sharedPreferences.edit().putString(KEY_USER_EMAIL, email).apply()
    }

    // Datos físicos
    fun getUserHeight(): Float? {
        val height = sharedPreferences.getFloat(KEY_USER_HEIGHT, -1f)
        return if (height != -1f) height else null
    }

    fun setUserHeight(height: Float) {
        sharedPreferences.edit().putFloat(KEY_USER_HEIGHT, height).apply()
    }

    fun getUserWeight(): Float? {
        val weight = sharedPreferences.getFloat(KEY_USER_WEIGHT, -1f)
        return if (weight != -1f) weight else null
    }

    fun setUserWeight(weight: Float) {
        sharedPreferences.edit().putFloat(KEY_USER_WEIGHT, weight).apply()
    }

    // Datos de entrenamiento
    fun getActivityLevel(): String? {
        return sharedPreferences.getString(KEY_ACTIVITY_LEVEL, null)
    }

    fun setActivityLevel(level: String) {
        sharedPreferences.edit().putString(KEY_ACTIVITY_LEVEL, level).apply()
    }

    fun getTrainingDays(): String? {
        return sharedPreferences.getString(KEY_TRAINING_DAYS, null)
    }

    fun setTrainingDays(days: String) {
        sharedPreferences.edit().putString(KEY_TRAINING_DAYS, days).apply()
    }

    fun getPreferredTime(): String? {
        return sharedPreferences.getString(KEY_PREFERRED_TIME, null)
    }

    fun setPreferredTime(time: String) {
        sharedPreferences.edit().putString(KEY_PREFERRED_TIME, time).apply()
    }

    // Datos de salud
    fun getHealthIssues(): String? {
        return sharedPreferences.getString(KEY_HEALTH_ISSUES, null)
    }

    fun setHealthIssues(issues: String) {
        sharedPreferences.edit().putString(KEY_HEALTH_ISSUES, issues).apply()
    }

    fun getMotivation(): String? {
        return sharedPreferences.getString(KEY_MOTIVATION, null)
    }

    fun setMotivation(motivation: String) {
        sharedPreferences.edit().putString(KEY_MOTIVATION, motivation).apply()
    }

    // Limpiar todos los datos
    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val PREFS_NAME = "GymAppPrefs"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_USER_HEIGHT = "user_height"
        private const val KEY_USER_WEIGHT = "user_weight"
        private const val KEY_ACTIVITY_LEVEL = "activity_level"
        private const val KEY_TRAINING_DAYS = "training_days"
        private const val KEY_PREFERRED_TIME = "preferred_time"
        private const val KEY_HEALTH_ISSUES = "health_issues"
        private const val KEY_MOTIVATION = "motivation"
    }
} 