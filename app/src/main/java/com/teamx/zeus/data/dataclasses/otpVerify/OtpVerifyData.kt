package com.teamx.zeus.data.models.otpVerify

import androidx.annotation.Keep


@Keep
data class OtpVerifyData(
    val permissions: List<String>,
    val token: String,
    val user: User
)