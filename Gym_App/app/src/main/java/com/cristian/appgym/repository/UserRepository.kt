package com.cristian.appgym.repository

import com.cristian.appgym.model.model_db.RegisterRequest
import com.cristian.appgym.model.model_db.Usuario
import com.cristian.appgym.model.model_db.UserData
import com.cristian.appgym.model.model_db.UserDataRequest
import com.cristian.appgym.network.ApiService
import com.cristian.appgym.utils.Result

class UserRepository(private val apiService: ApiService) {

    // ===== MÉTODOS DE VERIFICACIÓN =====
    
    // Verificar si el email ya existe
    suspend fun verificarEmailExistente(email: String): Result<Boolean> {
        return try {
            val response = apiService.checkEmail(email)
            if (response.isSuccessful && response.body() != null) {
                // Si encontramos un usuario con ese email, significa que ya existe
                Result.Success(true)
            } else {
                // Si no encontramos usuario, el email no existe
                Result.Success(false)
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Verificar si el username ya existe
    suspend fun verificarUsernameExistente(username: String): Result<Boolean> {
        return try {
            val response = apiService.checkUsername(username)
            if (response.isSuccessful && response.body() != null) {
                // Si encontramos un usuario con ese username, significa que ya existe
                Result.Success(true)
            } else {
                // Si no encontramos usuario, el username no existe
                Result.Success(false)
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // ===== MÉTODOS DE AUTENTICACIÓN =====
    
    // Login por username
    suspend fun loginByUsername(username: String, password: String): Result<Usuario> {
        return try {
            val response = apiService.loginByUsername(username)
            if (response.isSuccessful && response.body() != null) {
                val usuario = response.body()!!
                // Verificar que la contraseña coincida
                if (usuario.password == password) {
                    Result.Success(usuario)
                } else {
                    Result.Error("Contraseña incorrecta")
                }
            } else {
                Result.Error("Usuario no encontrado")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Login por email
    suspend fun loginByEmail(email: String, password: String): Result<Usuario> {
        return try {
            val response = apiService.loginByEmail(email)
            if (response.isSuccessful && response.body() != null) {
                val usuario = response.body()!!
                // Verificar que la contraseña coincida
                if (usuario.password == password) {
                    Result.Success(usuario)
                } else {
                    Result.Error("Contraseña incorrecta")
                }
            } else {
                Result.Error("Usuario no encontrado")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // ===== MÉTODOS DE USUARIO =====
    
    // Obtener usuario por username
    suspend fun obtenerUsuarioPorUsername(username: String): Result<Usuario> {
        return try {
            val response = apiService.getUserByUsername(username)
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
    suspend fun crearUsuario(registerRequest: RegisterRequest): Result<Usuario> {
        return try {
            val response = apiService.register(registerRequest)
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
            val response = apiService.getUserByEmail(email)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("Error al obtener usuario: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // Obtener datos básicos del usuario por ID
    suspend fun obtenerUsuario(id: Long): Result<Usuario> {
        return try {
            val response = apiService.getUserById(id)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error("No se pudieron obtener los datos del usuario")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }

    // ===== MÉTODOS DE DATOS ADICIONALES =====
    
    // Obtener datos adicionales del usuario
    suspend fun obtenerUserData(userId: Long): Result<UserData> {
        return try {
            val response = apiService.getUserData(userId)
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
            val response = apiService.saveUserData(userData)
            if (response.isSuccessful) {
                Result.Success(Unit)
            } else {
                Result.Error("Error al guardar datos de usuario: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Error de red: ${e.message}")
        }
    }
}
