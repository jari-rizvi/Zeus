package com.teamx.zues.dataclasses.createorder

import androidx.annotation.Keep

 
@Keep
data class Product(
    val order_quantity: Int,
    val product_id: String,
    val subtotal: Int,
    val unit_price: Int,
    val variation_option_id: String
)