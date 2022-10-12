package com.teamx.zeus.data.models.shopBySlug

import androidx.annotation.Keep


@Keep
data class Balance(
    val admin_commission_rate: Int,
    val payment_info: PaymentInfo
)