package dev.oure.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import dev.oure.workoutlog.databinding.ActivityLoginBinding
import dev.oure.workoutlog.models.LoginRequest
import dev.oure.workoutlog.models.LoginResponse
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
   lateinit var binding:ActivityLoginBinding
   lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

        binding.tvSignup.setOnClickListener {
            var intent =Intent(this, SignUpActivity::class.java)
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
        var error = false

        if (email.isBlank()){
            var error = true
            binding.tilEmail.error = "Email is required"
        }
        if (password.isBlank()){
            var error = true
            binding.tilEmail.error = "Email is required"
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            var error = true
            binding.tilEmail.error = "Email is invalid"
        }

        if (!error){
            val loginRequest = LoginRequest( email, password)
            makeloginRequest(loginRequest)
        }
    }
    fun makeloginRequest(loginRequest: LoginRequest){
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.loginUser(loginRequest)



    request.enqueue(object: retrofit2.Callback<LoginResponse>{
        override fun onResponse( call: Call<LoginResponse>,response: Response<LoginResponse>){
            if (response.isSuccessful){
                val loginResponse = response.body()
                persistLoginDetails(loginResponse!!)
                Toast.makeText(baseContext, loginResponse?.message, Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext, HomeActivity::class.java))

            }
            else{
                val error = response.errorBody()?.toString()
                Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
            }
        }
        override fun onFailure(call: Call<LoginResponse>, t: Throwable){
            Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
        }
   })
 }

    fun persistLoginDetails(loginResponse: LoginResponse){
        val editor = sharedPreferences.edit()
        editor.putString("USER_ID", loginResponse.userId)
        editor.putString("ACCESS_TOKEN", loginResponse.accessToken)
        editor.putString("PROFILE_ID", loginResponse.profileId)
        editor.apply()
    }
 }