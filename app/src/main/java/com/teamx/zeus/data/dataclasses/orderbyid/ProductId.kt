package com.teamx.zues.dataclasses.orderbyid

import com.teamx.zues.dataclasses.VariationOption

import androidx.annotation.Keep


@Keep
data class ProductId(
    val __v: Int,
    val _id: String,
    val categories: List<String>,
    val createdAt: String,
    val description: String,
    val gallery: List<String>,
    val image: String,
    val in_stock: Boolean,
    val is_taxable: Boolean,
    val max_price: Int,
    val min_price: Int,
    val name: String,
    val orders: List<String>,
    val product_type: String,
    val quantity: Int,
    val rating_count: List<Any>,
    val ratings: Int,
    val shop: String,
    val slug: String,
    val status: String,
    val tags: List<String>,
    val type: String,
    val unit: String,
    val updatedAt: String,
    val variation_options: List<VariationOption>,
    val variations: List<String>
)