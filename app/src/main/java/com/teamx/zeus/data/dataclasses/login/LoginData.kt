package com.teamx.zues.dataclasses.login

import androidx.annotation.Keep


@Keep
data class LoginData(
    val permissions: List<String>,
    val token: String,
    val user: UserX
)