package com.teamx.zeus.data.models.SignUp

data class SendCodeAttempt(
    val attempt_sid: String,
    val channel: String,
    val time: String
)