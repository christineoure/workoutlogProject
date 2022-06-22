package dev.oure.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.oure.workoutlog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
   lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignup.setOnClickListener {
            var intent =Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
                 validateLogin()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
    fun validateLogin(){
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
//        etEmail.add
        if (email.isBlank()){
            binding.tilEmail.error = "Email is required"
        }
        if (password.isBlank()){
            binding.tilEmail.error = "Email is required"
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmail.error = "Email is invalid"
        }
    }
}