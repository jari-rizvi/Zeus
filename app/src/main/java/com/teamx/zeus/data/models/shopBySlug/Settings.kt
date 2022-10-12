package com.teamx.zeus.data.models.shopBySlug

import androidx.annotation.Keep

 
@Keep
data class Settings(
    val contact: String,
    val socials: List<Social>,
    val website: String
)