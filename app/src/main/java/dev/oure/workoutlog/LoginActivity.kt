package dev.oure.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var tvSignup: TextView
    lateinit var etEmail: TextInputEditText
    lateinit var etPassword: TextInputEditText
    lateinit var btnLogin: Button
    lateinit var tilEmail: TextInputLayout
    lateinit var tilPassword: TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tvSignup=findViewById(R.id.tvSignup)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        btnLogin=findViewById(R.id.btnLogin)
        tilEmail=findViewById(R.id.tilEmail)
        tilPassword=findViewById(R.id.tilPassword)
        tvSignup.setOnClickListener {
            var intent =Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
                 validateLogin()
        }
    }
    fun validateLogin(){
        var email = etEmail.text.toString()
        var password = etPassword.text.toString()
//        etEmail.add
        if (email.isBlank()){
            tilEmail.error = "Email is required"
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tilEmail.error = "Email is invalid"
        }
    }
}