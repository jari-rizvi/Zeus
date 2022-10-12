package com.teamx.zues.dataclasses.dashboard

import androidx.annotation.Keep


@Keep
data class Dashboard(
    val __v: Int,
    val _id: String,
    val banners: List<Banner>,
    val createdAt: String,
    val promotional_sliders: List<String>,
    val updatedAt: String
)