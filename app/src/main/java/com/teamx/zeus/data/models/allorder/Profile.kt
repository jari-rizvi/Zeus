package com.teamx.zeus.data.models.allorder

import androidx.annotation.Keep


@Keep
data class Profile(
    val _id: String,
    val avatar: String,
    val bio: String,
    val contact: String
)