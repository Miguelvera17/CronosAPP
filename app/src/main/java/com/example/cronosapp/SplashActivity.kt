package com.example.cronosapp

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashLogo: ImageView = findViewById(R.id.splashLogo)
        val splashText: TextView = findViewById(R.id.splashText)
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        splashLogo.startAnimation(rotateAnimation)

        rotateAnimation.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
            override fun onAnimationStart(animation: android.view.animation.Animation?) {}
            override fun onAnimationEnd(animation: android.view.animation.Animation?) {

                splashText.visibility = TextView.VISIBLE
                val fadeInAnimation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.fade_in)
                splashText.startAnimation(fadeInAnimation)
                // Esperar un moment i passar a MainActivity
                fadeInAnimation.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                    override fun onAnimationStart(animation: android.view.animation.Animation?) {}
                    override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                        val intent = Intent(this@SplashActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
                })
            }
            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
        })
    }
}
