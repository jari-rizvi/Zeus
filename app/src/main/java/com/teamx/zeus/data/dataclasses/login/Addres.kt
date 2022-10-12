package com.teamx.zues.dataclasses.login

import com.teamx.zues.dataclasses.Address

import androidx.annotation.Keep

 
@Keep
data class Addres(
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