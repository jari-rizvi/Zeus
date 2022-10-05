package com.teamx.multivendor.dataclasses.profile

import com.teamx.multivendor.dataclasses.Profile

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