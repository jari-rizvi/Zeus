package com.teamx.zeus.data.models.allorder

data class BillingAddress(
    val _id: String,
    val city: String,
    val country: String,
    val state: String,
    val street_address: String,
    val zip: String
)