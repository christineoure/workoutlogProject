package dev.oure.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.oure.workoutlog.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
  lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogins.setOnClickListener {
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignUp.setOnClickListener {
            validate()
        }

    }
    fun validate(){
        var firstName = binding.etFirstName.text.toString()
        var secondName = binding.etSecondName.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var confirmpassword = binding.etConfirmPassword.text.toString()

        if (firstName.isBlank()){
            binding.tilFirstName.error="Required"
        }
        if (secondName.isBlank()){
            binding.tilSecondName.error="Required"
        }
        if (email.isBlank()){
            binding.tilEmail.error="Required"
        }
        if (password.isBlank()){
            binding.tilPassword.error="Required"
        }
        if (confirmpassword.isBlank()){
            binding.tilConfirmPassword.error="Required"
        }
        if (password != confirmpassword){
            binding.tilConfirmPassword.error="Password Error!"
        }

    }
}