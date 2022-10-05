package com.teamx.multivendor.dataclasses.settings

data class Location(
    val _id: String,
    val city: String,
    val country: String,
    val formattedAddress: String,
    val lat: Double,
    val lng: Double,
    val state: String
)