package com.example.cronosapp.ui.viewmodel

import android.util.Patterns
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

    fun login(email: String, username: String, password: String) {
        val validationResult = validateLogin(email, username, password)
        if (validationResult == null) {
            _loginStatus.value = LoginStatus.Success
        } else {
            _loginStatus.value = LoginStatus.Error(validationResult)
        }
    }

    fun validateLogin(email: String, username: String, password: String): String? {

        if (email.isEmpty()) {
            return "El campo email no puede estar vacío"
        }

        if (!email.contains("@")) {
            return "Formato de email debe contener @"
        }

        if (!email.contains(".com")) {
            return "Formato de email debe contener .com"
        }

        if (email.contains(" ")) {
            return "Formato de email no puede contener espacios"
        }

        if (username.isEmpty()) {
            return "El campo usuario no puede estar vacío"
        }

        if (username.contains(regex)) {
            return "Usuario incorrecto. No debe contener caracteres especiales"
        }

        if (password.isEmpty()) {
            return "El campo contraseña no puede estar vacío"
        }

        if (username == "usuario" || password == "contraseña") {
            return "Usuario o contraseña incorrectos"
        }

        if (password.length <= 8) {
            return "Contraseña demasiado corta. Debe tener mínimo 8 caracteres"
        }

        return null
    }
}
