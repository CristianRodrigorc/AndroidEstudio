package com.cristian.appgym.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.appgym.model.model_db.RegisterRequest
import com.cristian.appgym.model.model_db.Usuario
import com.cristian.appgym.repository.UserRepository
import com.cristian.appgym.utils.Result
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _usuario = MutableLiveData<Usuario?>()
    val usuario: LiveData<Usuario?> get() = _usuario

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val _emailExists = MutableLiveData<Boolean>()
    val emailExists: LiveData<Boolean> get() = _emailExists

    private val _usernameExists = MutableLiveData<Boolean>()
    val usernameExists: LiveData<Boolean> get() = _usernameExists

    // Login por username
    fun loginByUsername(username: String, password: String) {
        _isLoading.value = true
        _error.value = null
        
        viewModelScope.launch {
            when (val result = userRepository.loginByUsername(username, password)) {
                is Result.Success -> {
                    _usuario.value = result.data
                    _error.value = null
                }
                is Result.Error -> {
                    _usuario.value = null
                    _error.value = result.message
                }
                is Result.Loading -> {
                    // No necesitamos hacer nada aquí
                }
            }
            _isLoading.value = false
        }
    }

    // Login por email
    fun loginByEmail(email: String, password: String) {
        _isLoading.value = true
        _error.value = null
        
        viewModelScope.launch {
            when (val result = userRepository.loginByEmail(email, password)) {
                is Result.Success -> {
                    _usuario.value = result.data
                    _error.value = null
                }
                is Result.Error -> {
                    _usuario.value = null
                    _error.value = result.message
                }
                is Result.Loading -> {
                    // No necesitamos hacer nada aquí
                }
            }
            _isLoading.value = false
        }
    }

    // Registro
    fun register(registerRequest: RegisterRequest) {
        _isLoading.value = true
        _error.value = null
        
        viewModelScope.launch {
            when (val result = userRepository.crearUsuario(registerRequest)) {
                is Result.Success -> {
                    _usuario.value = result.data
                    _error.value = null
                }
                is Result.Error -> {
                    _usuario.value = null
                    _error.value = result.message
                }
                is Result.Loading -> {
                    // No necesitamos hacer nada aquí
                }
            }
            _isLoading.value = false
        }
    }

    // Verificar email
    fun verificarEmail(email: String) {
        viewModelScope.launch {
            when (val result = userRepository.verificarEmailExistente(email)) {
                is Result.Success -> {
                    _emailExists.value = result.data
                }
                is Result.Error -> {
                    _error.value = result.message
                }
                is Result.Loading -> {
                    // No necesitamos hacer nada aquí
                }
            }
        }
    }

    // Verificar username
    fun verificarUsername(username: String) {
        viewModelScope.launch {
            when (val result = userRepository.verificarUsernameExistente(username)) {
                is Result.Success -> {
                    _usernameExists.value = result.data
                }
                is Result.Error -> {
                    _error.value = result.message
                }
                is Result.Loading -> {
                    // No necesitamos hacer nada aquí
                }
            }
        }
    }

    // Limpiar errores
    fun clearError() {
        _error.value = null
    }

    // Limpiar usuario
    fun clearUsuario() {
        _usuario.value = null
    }
}
