package com.teamx.zues.dataclasses.categories

import androidx.annotation.Keep

 
@Keep
data class Settings(
    val isHome: Boolean,
    val layoutType: String,
    val productCard: String
)