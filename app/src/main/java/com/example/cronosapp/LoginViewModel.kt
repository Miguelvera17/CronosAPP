package com.example.cronosapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _loginStatus = MutableLiveData<LoginStatus>()
    val loginStatus: LiveData<LoginStatus> get() = _loginStatus
    val regex = ".*[\\.\\|\\$%&/·=].*".toRegex()

    sealed class LoginStatus {
        object Success : LoginStatus()
        data class Error(val message: String) : LoginStatus()
    }

    fun login(username: String, password: String) {
        val validationResult = validateLogin(username, password)
        if (validationResult == null) {
            _loginStatus.value = LoginStatus.Success
        } else {
            _loginStatus.value = LoginStatus.Error(validationResult)
        }
    }

    fun validateLogin(username: String, password: String): String? {

        if (username.isEmpty()) {
            return "Campo usuario no puede ser vacio"
        }

        if (password.isEmpty()) {
            return "Campo password no puede ser vacio"
        }

        if (username=="usuario" || password == "contraseña") {
            return "Usuario o contraseña incorrectos"
        }
        if (username.contains(regex)) {
            return "Usuario incorrecto. No debe contener caracteres especiales"
        }

        if (password.length <= 8) {
            return "Password demasiado corto. Debe ser minimo de 8 caracteres"
        }
        return null
    }
}
