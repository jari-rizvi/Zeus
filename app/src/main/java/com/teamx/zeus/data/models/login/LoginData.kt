package com.teamx.zeus.data.models.login

data class LoginData(
    val permissions: List<String>,
    val token: String,
    val user: User
)