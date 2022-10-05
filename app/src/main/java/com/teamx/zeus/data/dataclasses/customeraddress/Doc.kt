package com.teamx.multivendor.dataclasses.customeraddress

import com.teamx.multivendor.dataclasses.Address

data class Doc(
    val __v: Int,
    val _id: String,
    val address: Address,
    val createdAt: String,
    val customer: Customer,
    val default: Boolean,
    val title: String,
    val type: String,
    val updatedAt: String
)