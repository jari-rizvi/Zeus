package com.teamx.multivendor.dataclasses.settings

data class ContactDetails(
    val _id: String,
    val contact: String,
    val location: Location,
    val socials: List<Any>,
    val website: String
)