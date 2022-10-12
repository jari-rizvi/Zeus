package com.teamx.zues.dataclasses

import androidx.annotation.Keep


@Keep
data class Profile(
    val _id: String,
    val avatar: String,
    val bio: String
)