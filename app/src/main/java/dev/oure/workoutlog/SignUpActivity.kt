package dev.oure.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    lateinit var tvLogin: TextView
    lateinit var etFirstName: TextInputEditText
    lateinit var etSecondName: TextInputEditText
    lateinit var etEmail: TextInputEditText
    lateinit var etPassword: TextInputEditText
    lateinit var etConfirmPassword: TextInputEditText

    lateinit var btnSignUp: Button
    lateinit var tilFirstName: TextInputLayout
    lateinit var tilSecondName: TextInputLayout
    lateinit var tilEmail: TextInputLayout
    lateinit var tilPassword: TextInputLayout
    lateinit var tilConfirmPassword: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        tvLogin=findViewById(R.id.tvLogins)
        etFirstName=findViewById(R.id.etFirstName)
        etSecondName=findViewById(R.id.etSecondName)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        etConfirmPassword=findViewById(R.id.etConfirmPassword)

        btnSignUp=findViewById(R.id.btnSignUp)
        tilFirstName=findViewById(R.id.tilFirstName)
        tilSecondName=findViewById(R.id.tilSecondName)
        tilEmail=findViewById(R.id.tilEmail)
        tilPassword=findViewById(R.id.tilPassword)
        tilConfirmPassword=findViewById(R.id.tilConfirmPassword)

        tvLogin.setOnClickListener {
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        btnSignUp.setOnClickListener {
            validate()
        }

    }
    fun validate(){
        var firstName = etFirstName.text.toString()
        var secondName = etSecondName.text.toString()
        var email = etEmail.text.toString()
        var password = etPassword.text.toString()
        var confirmpassword = etConfirmPassword.text.toString()

        if (firstName.isBlank()){
            tilFirstName.error="Required"
        }
        if (secondName.isBlank()){
            tilSecondName.error="Required"
        }
        if (email.isBlank()){
            tilEmail.error="Required"
        }
        if (password.isBlank()){
            tilPassword.error="Required"
        }
        if (confirmpassword.isBlank()){
            tilConfirmPassword.error="Required"
        }
        if (password != confirmpassword){
            tilConfirmPassword.error="Password Error!"
        }

    }
}