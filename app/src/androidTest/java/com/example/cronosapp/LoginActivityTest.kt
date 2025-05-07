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
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // Se ejecuta antes de cada prueba, si es necesario.
    }

//    @Test
//    fun testLoginWithValidCredentials() {
//        onView(withId(R.id.editTextEmail))
//            .perform(replaceText("usuario@correo.com"), closeSoftKeyboard())
//        onView(withId(R.id.editTextUser))
//            .perform(replaceText("usuario123"), closeSoftKeyboard())
//        onView(withId(R.id.editTextExample))
//            .perform(replaceText("Contraseña1"), closeSoftKeyboard())
//        onView(withId(R.id.loginButton)).perform(click())
//
//        onView(withId(R.id.textViewMessage)).check(matches(withText("Registro exitoso")))
//            .check(matches(isDisplayed()))
//    }

    @Test
    fun testLoginEmptyEmail() {
        onView(withId(R.id.editTextEmail))
            .perform(replaceText(""), closeSoftKeyboard())
        onView(withId(R.id.editTextUser))
            .perform(replaceText("usuario123"), closeSoftKeyboard())
        onView(withId(R.id.editTextExample))
            .perform(replaceText("Contraseña1"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.textViewMessage)).check(matches(withText("El campo email no puede estar vacío")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginEmailWithoutCOM() {
        onView(withId(R.id.editTextEmail))
            .perform(replaceText("usuario@correo"), closeSoftKeyboard())
        onView(withId(R.id.editTextUser))
            .perform(replaceText("usuario123"), closeSoftKeyboard())
        onView(withId(R.id.editTextExample))
            .perform(replaceText("Contraseña1"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.textViewMessage))
            .check(matches(withText("Formato de email debe contener .com")))
            .check(matches(isDisplayed()))
    }


    @Test
    fun testLoginEmailWithoutAT() {
        onView(withId(R.id.editTextEmail))
            .perform(replaceText("usuariocorreo.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextUser))
            .perform(replaceText("usuario123"), closeSoftKeyboard())
        onView(withId(R.id.editTextExample))
            .perform(replaceText("Contraseña1"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.textViewMessage)).check(matches(withText("Formato de email debe contener @")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginEmailWithSpace() {
        onView(withId(R.id.editTextEmail))
            .perform(replaceText("usuario @correo.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextUser))
            .perform(replaceText("usuario123"), closeSoftKeyboard())
        onView(withId(R.id.editTextExample))
            .perform(replaceText("Contraseña1"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.textViewMessage)).check(matches(withText("Formato de email no puede contener espacios")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginEmptyUser() {
        onView(withId(R.id.editTextEmail))
            .perform(replaceText("usuario@correo.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextUser))
            .perform(replaceText(""), closeSoftKeyboard())
        onView(withId(R.id.editTextExample))
            .perform(replaceText("Contraseña1"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.textViewMessage)).check(matches(withText("El campo usuario no puede estar vacío")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginInvalidUser() {
        onView(withId(R.id.editTextEmail))
            .perform(replaceText("usuario@correo.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextUser))
            .perform(replaceText("invalidUser."), closeSoftKeyboard())
        onView(withId(R.id.editTextExample))
            .perform(replaceText("Contraseña1"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.textViewMessage)).check(matches(withText("Usuario incorrecto. No debe contener caracteres especiales")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginInvalidPass() {
        onView(withId(R.id.editTextEmail))
            .perform(typeText("usuario@correo.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextUser))
            .perform(typeText("usuario123"), closeSoftKeyboard())
        onView(withId(R.id.editTextExample))
            .perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.textViewMessage)).check(matches(withText("Contraseña demasiado corta. Debe tener mínimo 8 caracteres")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginEmptyPass() {
        onView(withId(R.id.editTextEmail))
            .perform(typeText("usuario@correo.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextUser))
            .perform(typeText("usuario123"), closeSoftKeyboard())
        onView(withId(R.id.editTextExample))
            .perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.textViewMessage)).check(matches(withText("El campo contraseña no puede estar vacío")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginInvalidCredentials() {
        onView(withId(R.id.editTextEmail))
            .perform(replaceText("usuario@correo.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextUser))
            .perform(replaceText("usuario"), closeSoftKeyboard())
        onView(withId(R.id.editTextExample))
            .perform(replaceText("contraseña"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.textViewMessage)).check(matches(withText("Usuario o contraseña incorrectos")))
            .check(matches(isDisplayed()))
    }
}
