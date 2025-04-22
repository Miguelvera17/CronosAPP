package com.example.cronosapp

import com.example.cronosapp.ui.viewmodel.LoginViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel()
    }

    @Test
    fun testValidUser() {
        val validUser = "validUser"
        val validPassword = "ValidPassword123"

        // Cambiar el resultado a un String? y comprobar si es null (validación exitosa)
        val result = loginViewModel.validateLogin(validUser, validPassword)
        assertNull(result)  // Si el resultado es null, la validación es exitosa
    }

    @Test
    fun testInvalidUser() {
        val invalidUser = "invalidUser"
        val validPassword = "ValidPassword123"

        // Cambiar el resultado a un String? y comprobar si el mensaje de error es el esperado
        val result = loginViewModel.validateLogin(invalidUser, validPassword)
        assertEquals("Usuario o contraseña incorrectos", result)  // El mensaje de error esperado
    }

    @Test
    fun testInvalidPassword() {
        val validUser = "validUser"
        val invalidPassword = "invalid"

        // Cambiar el resultado a un String? y comprobar si el mensaje de error es el esperado
        val result = loginViewModel.validateLogin(validUser, invalidPassword)
        assertEquals("Usuario o contraseña incorrectos", result)  // El mensaje de error esperado
    }
}
