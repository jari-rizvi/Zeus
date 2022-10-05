package com.teamx.multivendor.dataclasses.dashboard

import com.teamx.multivendor.dataclasses.PaymentInfo

data class BalanceX(
    val _id: String,
    val admin_commission_rate: Int,
    val current_balance: Int,
    val payment_info: PaymentInfo,
    val total_earnings: Int,
    val withdrawn_amount: Int
)