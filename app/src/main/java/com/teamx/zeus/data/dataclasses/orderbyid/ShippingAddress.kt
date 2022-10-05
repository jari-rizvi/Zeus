package com.teamx.multivendor.dataclasses.orderbyid

data class ShippingAddress(
    val _id: String,
    val city: String,
    val country: String,
    val state: String,
    val street_address: String,
    val zip: String
)