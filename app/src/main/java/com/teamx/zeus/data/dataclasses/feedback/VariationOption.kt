package com.teamx.zues.dataclasses.feedback

import com.teamx.zues.dataclasses.Option

import androidx.annotation.Keep

 
@Keep
data class VariationOption(
    val _id: String,
    val createdAt: String,
    val is_disable: Boolean,
    val options: List<Option>,
    val price: Int,
    val quantity: Int,
    val sale_price: Int,
    val sku: String,
    val title: String,
    val updatedAt: String
)