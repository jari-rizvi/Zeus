package com.teamx.multivendor.dataclasses.products

import com.teamx.multivendor.dataclasses.SettingsX

data class Type(
    val __v: Int,
    val _id: String,
    val banners: List<String>,
    val createdAt: String,
    val icon: String,
    val name: String,
    val promotional_sliders: List<String>,
    val settings: SettingsX,
    val slug: String,
    val updatedAt: String
)