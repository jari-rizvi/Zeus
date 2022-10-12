package com.teamx.zeus.data.models.shopBySlug

import androidx.annotation.Keep


@Keep
data class Address(
    val city: String,
    val country: String,
    val state: String,
    val street_address: String,
    val zip: String
)