package com.teamx.zeus.data.models.Dashboard

data class VariationOption(
    val _id: String,
    val createdAt: String,
    val is_disable: Boolean,
    val options: List<Option>,
    val price: Int,
    val quantity: Int,
    val sku: String,
    val title: String,
    val updatedAt: String
)