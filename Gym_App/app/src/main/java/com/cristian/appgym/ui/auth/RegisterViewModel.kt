package com.cristian.appgym.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.appgym.data.AuthService
import com.cristian.appgym.data.model.RegisterRequest
import com.cristian.appgym.data.model.RegisterResponse
import com.cristian.appgym.data.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(private val authService: AuthService) : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    private val _emailCheckState = MutableStateFlow<Boolean?>(null)
    val emailCheckState: StateFlow<Boolean?> = _emailCheckState

    private val _usernameCheckState = MutableStateFlow<Boolean?>(null)
    val usernameCheckState: StateFlow<Boolean?> = _usernameCheckState

    fun checkEmail(email: String) {
        viewModelScope.launch {
            try {
                val response = authService.checkEmail(email)
                if (response.isSuccessful) {
                    _emailCheckState.value = response.body()
                } else {
                    _emailCheckState.value = null
                }
            } catch (e: Exception) {
                _emailCheckState.value = null
            }
        }
    }

    fun checkUsername(username: String) {
        viewModelScope.launch {
            try {
                val response = authService.checkUsername(username)
                if (response.isSuccessful) {
                    _usernameCheckState.value = response.body()
                } else {
                    _usernameCheckState.value = null
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
                val response = authService.register(request)
                if (response.isSuccessful) {
                    _registerState.value = RegisterState.Success(response.body()!!.usuario)
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