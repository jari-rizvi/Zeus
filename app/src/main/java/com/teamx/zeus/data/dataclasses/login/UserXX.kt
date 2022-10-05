package com.teamx.multivendor.dataclasses.login

import com.teamx.multivendor.dataclasses.Profile
import com.teamx.multivendor.dataclasses.profile.AddresX

data class UserXX(
    val __v: Int,
    val _id: String,
    val address: List<AddresX>,
    val contact: String,
    val contact_verified: Boolean,
    val createdAt: String,
    val email: String,
    val email_verified: Boolean,
    val is_active: Boolean,
    val name: String,
    val password: String,
    val profile: Profile,
    val roles: List<String>,
    val shops: List<Any>,
    val updatedAt: String
)