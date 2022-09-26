package com.teamx.zeus.data.models.resendOtp

data class ResendOtpData(
    val id: String,
    val message: String,
    val phone_number: String,
    val provider: String,
    val success: Boolean,
    val twilio: Twilio
)