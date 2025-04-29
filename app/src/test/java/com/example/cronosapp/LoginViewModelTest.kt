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
        val validUser = "usuario2"
        val validPassword = "ValidPassword123"
        val result = loginViewModel.validateLogin(validUser, validPassword)
        assertNull(result)
    }

    @Test
    fun testEmptyUser() {
        val invalidUser = ""
        val validPassword = "ValidPassword123"
        val result = loginViewModel.validateLogin(invalidUser, validPassword)
        assertEquals("Campo usuario no puede ser vacio", result)
    }

    @Test
    fun testInvalidUser() {
        val invalidUser = "invalidUser."
        val validPassword = "ValidPassword123"
        val result = loginViewModel.validateLogin(invalidUser, validPassword)
        assertEquals("Usuario incorrecto. No debe contener caracteres especiales", result)
    }

    @Test
    fun testInvalidPassword() {
        val validUser = "validUser"
        val invalidPassword = "invalid"

        val result = loginViewModel.validateLogin(validUser, invalidPassword)
        assertEquals("Password demasiado corto. Debe ser minimo de 8 caracteres", result)
    }

    @Test
    fun testEmptyPassword() {
        val validUser = "validUser"
        val validPassword = ""
        val result = loginViewModel.validateLogin(validUser, validPassword)
        assertEquals("Campo password no puede ser vacio", result)
    }
}
