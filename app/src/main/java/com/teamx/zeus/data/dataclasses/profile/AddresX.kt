package com.teamx.multivendor.dataclasses.profile

import com.teamx.multivendor.dataclasses.Address

data class AddresX(
    val __v: Int,
    val _id: String,
    val address: Address,
    val createdAt: String,
    val customer: String,
    val default: Boolean,
    val title: String,
    val type: String,
    val updatedAt: String
)