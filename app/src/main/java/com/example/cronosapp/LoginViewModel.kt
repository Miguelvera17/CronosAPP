package com.example.cronosapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginStatus = MutableLiveData<LoginStatus>()
    val loginStatus: LiveData<LoginStatus> get() = _loginStatus

    // Definir el estado de login (exitoso o con error)
    sealed class LoginStatus {
        object Success : LoginStatus()
        data class Error(val message: String) : LoginStatus()
    }

    // Método para validar las credenciales de login
    fun login(username: String, password: String) {
        val validationResult = validateLogin(username, password)
        if (validationResult == null) {
            // Si las credenciales son válidas, consideramos que el login es exitoso
            _loginStatus.value = LoginStatus.Success
        } else {
            // Si hay un error de validación, se pasa el mensaje de error
            _loginStatus.value = LoginStatus.Error(validationResult)
        }
    }

    // Método que valida los campos de usuario y contraseña
    fun validateLogin(username: String, password: String): String? {
        // Verificar que los campos no estén vacíos
        if (username.isEmpty() || password.isEmpty()) {
            return "Los campos no pueden estar vacíos"
        }

        // Verificar que el nombre de usuario y la contraseña sean correctos (ejemplo simple)
        if (username != "usuario" || password != "contraseña") {
            return "Usuario o contraseña incorrectos"
        }

        // Si pasa todas las validaciones, se devuelve null
        return null
    }
}
