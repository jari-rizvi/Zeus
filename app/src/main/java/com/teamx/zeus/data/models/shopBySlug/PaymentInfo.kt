package com.teamx.zeus.data.models.shopBySlug

import androidx.annotation.Keep

 
@Keep
data class PaymentInfo(
    val account: String,
    val bank: String,
    val email: String,
    val name: String
)