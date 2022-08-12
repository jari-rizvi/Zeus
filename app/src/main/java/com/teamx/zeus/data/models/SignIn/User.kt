package com.teamx.zeus.data.models.SignIn

data class User(
    val _id: String,
    val address: String,
    val email: String,
    val firstName: String,
    val fullName: String,
    val lastName: String,
    val phone: String,
    val role: String
)