package com.cristian.appgym.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.appgym.model.UsuarioCompleto
import com.cristian.appgym.repository.UserRepository
import com.cristian.appgym.utils.Result
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _usuarioCompleto = MutableLiveData<UsuarioCompleto?>()
    val usuarioCompleto: LiveData<UsuarioCompleto?> get() = _usuarioCompleto

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun obtenerUsuarioCompleto(email: String) {
        viewModelScope.launch {
            when (val result = userRepository.obtenerUsuarioCompleto(email)) {
                is Result.Success -> {
                    _usuarioCompleto.value = result.data
                    _error.value = null
                }
                is Result.Error -> {
                    _usuarioCompleto.value = null
                    _error.value = result.message
                }
                is Result.Loading -> {
                    // No necesitamos hacer nada aquí ya que estamos usando LiveData
                }
            }
        }
    }
}
