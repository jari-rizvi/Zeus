package com.teamx.zeus.data.models.otpVerify

data class OtpVerifyData(
    val permissions: List<String>,
    val token: String,
    val user: User
)