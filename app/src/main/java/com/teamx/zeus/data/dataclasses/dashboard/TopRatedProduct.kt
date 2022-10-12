package com.teamx.zues.dataclasses.dashboard

import com.teamx.zues.dataclasses.VariationOption

import androidx.annotation.Keep


@Keep
data class TopRatedProduct(
    val __v: Int,
    val _id: String,
    val categories: List<Category>,
    val createdAt: String,
    val description: String,
    val gallery: List<String>,
    val height: String,
    val image: String,
    val in_stock: Boolean,
    val is_taxable: Boolean,
    val length: String,
    val max_price: Int,
    val min_price: Int,
    val name: String,
    val orders: List<String>,
    val price: Int,
    val product_type: String,
    val quantity: Int,
    val rating_count: List<RatingCount>,
    val ratings: Int,
    val sale_price: Any,
    val shop: ShopX,
    val sku: String,
    val slug: String,
    val status: String,
    val tags: List<Tag>,
    val type: TypeX,
    val unit: String,
    val updatedAt: String,
    val variation_options: List<VariationOption>,
    val variations: List<Variation>,
    val width: String
)