package com.teamx.zeus.data.models.ResetPass

import androidx.annotation.Keep


@Keep
data class ResetPassData(
    val message: String,
    val success: Boolean
)