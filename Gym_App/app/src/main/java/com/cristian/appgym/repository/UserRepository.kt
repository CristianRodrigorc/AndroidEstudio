package com.cristian.appgym.repository

import com.cristian.appgym.model.UserData
import com.cristian.appgym.model.UserDataRequest
import com.cristian.appgym.model.Usuario
import com.cristian.appgym.model.UsuarioCompleto
import com.cristian.appgym.network.ApiService
import com.cristian.appgym.utils.Result
import retrofit2.Response

class UserRepository(private val apiService: ApiService) {

    // Verificar si el email ya existe
    suspend fun verificarEmailExistente(email: String): Result<Boolean> {
        return try {
            val response = apiService.verificarEmail(email)
            if (response.isSuccessful) {
                Result.Success(response.body() ?: false)
            } else {
                // Si es error 401, asumimos que el email no existe
                if (response.code() == 401) {
                    Result.Success(false)
                } else {
                    Result.Error("Error al verificar email: ${response.message()}")
                }
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Verificar si el username ya existe
    suspend fun verificarUsernameExistente(username: String): Result<Boolean> {
        return try {
            val response = apiService.verificarUsername(username)
            if (response.isSuccessful) {
                Result.Success(response.body() ?: false)
            } else {
                // Si es error 401, asumimos que el username no existe
                if (response.code() == 401) {
                    Result.Success(false)
                } else {
                    Result.Error("Error al verificar username: ${response.message()}")
                }
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Obtener usuario por username
    suspend fun obtenerUsuarioPorUsername(username: String): Result<Usuario> {
        return try {
            val response = apiService.obtenerUsuarioPorUsername(username)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("Usuario no encontrado")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Crear un nuevo usuario
    suspend fun crearUsuario(usuario: Usuario): Result<Usuario> {
        return try {
            val response = apiService.crearUsuario(usuario)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("Error al crear usuario: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Obtener usuario por email
    suspend fun obtenerUsuarioPorEmail(email: String): Result<Usuario> {
        return try {
            val response = apiService.obtenerUsuarioPorEmail(email)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("Error al obtener usuario: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Obtener datos básicos del usuario
    suspend fun obtenerUsuario(id: Long): Result<Usuario> {
        return try {
            val response = apiService.obtenerUsuario(id)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("No se pudieron obtener los datos del usuario")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Obtener datos adicionales del usuario
    suspend fun obtenerUserData(userId: Long): Result<UserData> {
        return try {
            val response = apiService.obtenerUserData(userId)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("No se pudieron obtener los datos adicionales del usuario")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Guardar datos adicionales del usuario
    suspend fun guardarUserData(userData: UserDataRequest): Result<Unit> {
        return try {
            val response = apiService.guardarUserData(userData)
            if (response.isSuccessful) {
                Result.Success(Unit)
            } else {
                Result.Error("Error al guardar datos de usuario: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Obtener los datos completos del usuario
    suspend fun obtenerUsuarioCompleto(email: String): Result<UsuarioCompleto> {
        return try {
            val usuarioResult = obtenerUsuarioPorEmail(email)
            if (usuarioResult is Result.Success) {
                val usuario = usuarioResult.data
                val userId = usuario.id_user ?: usuario.userId
                if (userId != null) {
                    val userDataResult = obtenerUserData(userId)
                    if (userDataResult is Result.Success) {
                        Result.Success(UsuarioCompleto(usuario, userDataResult.data))
                    } else {
                        Result.Error("Error al obtener datos adicionales del usuario")
                    }
                } else {
                    Result.Error("El usuario no tiene un ID válido")
                }
            } else {
                Result.Error("Error al obtener usuario")
            }
        } catch (e: Exception) {
            Result.Error("Error al obtener usuario completo: ${e.message}")
        }
    }
}
