package com.teamx.zeus.data.models.resendOtp

data class SendCodeAttempt(
    val attempt_sid: String,
    val channel: String,
    val time: String
)