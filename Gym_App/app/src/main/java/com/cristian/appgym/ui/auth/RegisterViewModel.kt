package com.cristian.appgym.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.appgym.network.ApiService
import com.cristian.appgym.model.model_db.RegisterRequest
import com.cristian.appgym.model.model_db.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(private val apiService: ApiService) : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    private val _emailCheckState = MutableStateFlow<Boolean?>(null)
    val emailCheckState: StateFlow<Boolean?> = _emailCheckState

    private val _usernameCheckState = MutableStateFlow<Boolean?>(null)
    val usernameCheckState: StateFlow<Boolean?> = _usernameCheckState

    fun checkEmail(email: String) {
        viewModelScope.launch {
            try {
                val response = apiService.checkEmail(email)
                if (response.isSuccessful && response.body() != null) {
                    // Si encontramos un usuario, el email ya existe
                    _emailCheckState.value = true
                } else {
                    // Si no encontramos usuario, el email no existe
                    _emailCheckState.value = false
                }
            } catch (e: Exception) {
                _emailCheckState.value = null
            }
        }
    }

    fun checkUsername(username: String) {
        viewModelScope.launch {
            try {
                val response = apiService.checkUsername(username)
                if (response.isSuccessful && response.body() != null) {
                    // Si encontramos un usuario, el username ya existe
                    _usernameCheckState.value = true
                } else {
                    // Si no encontramos usuario, el username no existe
                    _usernameCheckState.value = false
                }
            } catch (e: Exception) {
                _usernameCheckState.value = null
            }
        }
    }

    fun register(request: RegisterRequest) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            try {
                val response = apiService.register(request)
                if (response.isSuccessful && response.body() != null) {
                    _registerState.value = RegisterState.Success(response.body()!!)
                } else {
                    _registerState.value = RegisterState.Error("Error al registrar usuario")
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val usuario: Usuario) : RegisterState()
    data class Error(val message: String) : RegisterState()
} 