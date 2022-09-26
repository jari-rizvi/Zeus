package com.teamx.zeus.data.models.forgotPass

data class ForgotData(
    val email: String,
    val message: String,
    val success: Boolean,
    val token: String
)