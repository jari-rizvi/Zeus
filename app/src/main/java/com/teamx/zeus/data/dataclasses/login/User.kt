package com.teamx.multivendor.dataclasses.login

data class User(
    val permissions: List<String>,
    val token: String,
    val user: UserXX
)