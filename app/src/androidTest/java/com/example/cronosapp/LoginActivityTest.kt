package com.example.cronosapp

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // Se ejecuta antes de cada prueba, si es necesario.
    }

    @Test
    fun testLoginWithValidCredentials() {
        // Ingresa el nombre de usuario y la contraseña
        onView(withId(R.id.editTextUser))
            .perform(typeText("validUser"), closeSoftKeyboard())

        onView(withId(R.id.editTextExample))
            .perform(typeText("ValidPassword123"), closeSoftKeyboard())

        // Clic en el botón de login
        onView(withId(R.id.loginButton)).perform(click())

        // Verifica que se navegue a la siguiente pantalla o muestre un mensaje de éxito
        onView(withText("Login Successful"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginWithInvalidCredentials() {
        // Ingresa un nombre de usuario y contraseña inválidos
        onView(withId(R.id.editTextUser))
            .perform(typeText("invalidUser"), closeSoftKeyboard())

        onView(withId(R.id.editTextExample))
            .perform(typeText("invalidPassword"), closeSoftKeyboard())

        // Clic en el botón de login
        onView(withId(R.id.loginButton)).perform(click())

        // Verifica que aparezca un mensaje de error
        onView(withText("Invalid credentials"))
            .check(matches(isDisplayed()))
    }
}
