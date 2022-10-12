package com.teamx.zues.dataclasses.dashboard

import androidx.annotation.Keep

 
@Keep
data class Settings(
    val _id: String,
    val contact: String,
    val location: Location,
    val socials: List<Any>,
    val website: String
)