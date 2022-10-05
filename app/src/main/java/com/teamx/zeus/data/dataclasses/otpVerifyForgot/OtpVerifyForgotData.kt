package com.teamx.zeus.data.models.otpVerifyForgot

data class OtpVerifyForgotData(
    val message: String,
    val success: Boolean,
    val token: String
)