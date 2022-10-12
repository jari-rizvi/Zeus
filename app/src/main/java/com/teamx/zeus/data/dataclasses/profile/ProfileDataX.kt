package com.teamx.zues.dataclasses.profile

import com.teamx.zues.dataclasses.Profile

import androidx.annotation.Keep


@Keep
data class ProfileDataX(
    val __v: Int,
    val _id: String,
    val address: List<AddresX>,
    val createdAt: String,
    val email: String,
    val is_active: Boolean,
    val name: String,
    val password: String,
    val profile: Profile,
    val roles: List<String>,
    val shops: List<Any>,
    val updatedAt: String
)