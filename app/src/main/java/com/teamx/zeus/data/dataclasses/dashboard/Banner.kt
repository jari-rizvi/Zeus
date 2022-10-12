package com.teamx.zues.dataclasses.dashboard

import androidx.annotation.Keep

 
@Keep
data class Banner(
    val _id: String,
    val description: String,
    val image: String,
    val title: String
)