package com.example.appmv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.appmv.View.MainActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DURATION = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val runnable = Runnable {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val handler = Handler()
        handler.postDelayed(runnable, SPLASH_DURATION)

    }
}
