package dev.oure.workoutlog.models

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    var email: String,
    var password: String,
)
