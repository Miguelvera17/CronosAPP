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
    fun testValidCredentials() {
        val validEmail = "usuario@correo.com"
        val validUser = "usuario2"
        val validPassword = "ValidPassword123"
        val result = loginViewModel.validateLogin(validEmail, validUser, validPassword)
        assertNull(result)
    }

    @Test
    fun testEmptyEmail() {
        val invalidEmail = ""
        val validUser = "usuario2"
        val validPassword = "ValidPassword123"
        val result = loginViewModel.validateLogin(invalidEmail, validUser, validPassword)
        assertEquals("El campo email no puede estar vacío", result)
    }

    @Test
    fun testInvalidEmailWithoutAT() {
        val invalidEmail = "usuariocorreo.com"
        val validUser = "usuario2"
        val validPassword = "ValidPassword123"
        val result = loginViewModel.validateLogin(invalidEmail, validUser, validPassword)
        assertEquals("Formato de email debe contener @", result)
    }

    @Test
    fun testInvalidEmailWithoutCOM() {
        val invalidEmail = "usuario@correo"
        val validUser = "usuario2"
        val validPassword = "ValidPassword123"
        val result = loginViewModel.validateLogin(invalidEmail, validUser, validPassword)
        assertEquals("Formato de email debe contener .com", result)
    }

    @Test
    fun testInvalidEmailWithSpace() {
        val invalidEmail = "usuario @correo.com"
        val validUser = "usuario2"
        val validPassword = "ValidPassword123"
        val result = loginViewModel.validateLogin(invalidEmail, validUser, validPassword)
        assertEquals("Formato de email no puede contener espacios", result)
    }

    @Test
    fun testEmptyUser() {
        val validEmail = "test@example.com"
        val invalidUser = ""
        val validPassword = "ValidPassword123"
        val result = loginViewModel.validateLogin(validEmail, invalidUser, validPassword)
        assertEquals("El campo usuario no puede estar vacío", result)
    }

    @Test
    fun testInvalidUserCharacters() {
        val validEmail = "test@example.com"
        val invalidUser = "invalidUser."
        val validPassword = "ValidPassword123"
        val result = loginViewModel.validateLogin(validEmail, invalidUser, validPassword)
        assertEquals("Usuario incorrecto. No debe contener caracteres especiales", result)
    }

    @Test
    fun testEmptyPassword() {
        val validEmail = "test@example.com"
        val validUser = "usuario2"
        val emptyPassword = ""
        val result = loginViewModel.validateLogin(validEmail, validUser, emptyPassword)
        assertEquals("El campo contraseña no puede estar vacío", result)
    }

    @Test
    fun testShortPassword() {
        val validEmail = "test@example.com"
        val validUser = "usuario2"
        val shortPassword = "12345"
        val result = loginViewModel.validateLogin(validEmail, validUser, shortPassword)
        assertEquals("Contraseña demasiado corta. Debe tener mínimo 8 caracteres", result)
    }

    @Test
    fun testInvalidCredentials() {
        val validEmail = "test@example.com"
        val blockedUser = "usuario"
        val blockedPassword = "contraseña"
        val result = loginViewModel.validateLogin(validEmail, blockedUser, blockedPassword)
        assertEquals("Usuario o contraseña incorrectos", result)
    }
}
