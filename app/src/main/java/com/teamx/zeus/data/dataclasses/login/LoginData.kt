package com.teamx.multivendor.dataclasses.login

data class LoginData(
    val permissions: List<String>,
    val token: String,
    val user: UserX
)