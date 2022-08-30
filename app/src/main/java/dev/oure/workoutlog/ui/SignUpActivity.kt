package dev.oure.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.oure.workoutlog.databinding.ActivitySignUpBinding
import dev.oure.workoutlog.models.RegisterRequest
import dev.oure.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
  lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogins.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
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
        var phonenumber = binding.etPhoneNumber.text.toString()
        var password = binding.etPassword.text.toString()
        var confirmpassword = binding.etConfirmPassword.text.toString()

        var error = false

        if (firstName.isBlank()){
            error = true
            binding.tilFirstName.error="First Name Required"
        }
        if (secondName.isBlank()){
            error = true
            binding.tilSecondName.error=" Second Name Required"
        }
        if (email.isBlank()){
            error = true
            binding.tilEmail.error="Email is Required"
        }
        if (phonenumber.isBlank()){
            error = true
            binding.tilPhoneNumber.error="Phone number is Required"
        }
        if (password.isBlank()){
            error = true
            binding.tilPassword.error="Password Required"
        }
        if (confirmpassword.isBlank()){
            error = true
            binding.tilConfirmPassword.error="Passwords do not match"
        }
        if (password != confirmpassword){
            error = true
            binding.tilConfirmPassword.error="Password Error!"
        }
        if (!error){
            var registerRequest = RegisterRequest( firstName, secondName, phonenumber, email, password)
            makeRegistrationRequest(registerRequest)
        }

}
    fun makeRegistrationRequest(registerRequest: RegisterRequest){
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.registerUser(registerRequest)

        request.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    var message = response.body()?.message
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext, LoginActivity::class.java))
                }
                else{
                    var error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}