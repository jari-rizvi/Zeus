package com.teamx.multivendor.dataclasses

data class PaymentInfo(
    val _id: String,
    val account: String,
    val bank: String,
    val email: String,
    val name: String
)