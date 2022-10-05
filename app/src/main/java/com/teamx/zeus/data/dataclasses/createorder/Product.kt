package com.teamx.multivendor.dataclasses.createorder

data class Product(
    val order_quantity: Int,
    val product_id: String,
    val subtotal: Int,
    val unit_price: Int,
    val variation_option_id: String
)