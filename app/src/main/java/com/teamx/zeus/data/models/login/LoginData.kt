package com.teamx.zeus.data.models.login

import androidx.annotation.Keep

 
@Keep
data class LoginData(
    val permissions: List<String>,
    val token: String,
    val user: User
)