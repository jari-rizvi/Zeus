package com.teamx.zeus.data.models.SignUp

data class RegisterData(
    val id: String,
    val message: String,
    val phone_number: String,
    val provider: String,
    val success: Boolean,
    val twilio: Twilio
)