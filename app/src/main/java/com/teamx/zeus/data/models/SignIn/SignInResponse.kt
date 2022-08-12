package com.teamx.zeus.data.models.SignIn

data class SignInResponse(
    val flag: Int,
    val message: String,
    val token: String,
    val user: User
)