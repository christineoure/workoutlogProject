package dev.oure.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.oure.workoutlog.R

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

        var accessToken = sharedPreferences.getString("ACCESS_TOKEN", "").toString()
        if (accessToken.isBlank()){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        setContentView(R.layout.activity_splash)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}