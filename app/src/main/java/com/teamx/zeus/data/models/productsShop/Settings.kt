package com.teamx.zeus.data.models.productsShop

data class Settings(
    val _id: String,
    val contact: String,
    val location: Location,
    val socials: List<Any>,
    val website: String
)