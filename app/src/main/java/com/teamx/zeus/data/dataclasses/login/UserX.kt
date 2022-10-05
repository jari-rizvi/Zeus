package com.teamx.multivendor.dataclasses.login

import com.teamx.multivendor.dataclasses.Profile

data class UserX(
    val __v: Int,
    val _id: String,
    val address: List<Addres>,
    val createdAt: String,
    val email: String,
    val is_active: Boolean,
    val contact_verified: Boolean,
    var contact: String,
    val email_verified: Boolean,
    val name: String,
    val password: String,
    val profile: Profile,
    val roles: List<String>,
    val shops: List<Any>,
    val updatedAt: String
)