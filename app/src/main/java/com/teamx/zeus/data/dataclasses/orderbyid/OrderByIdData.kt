package com.teamx.multivendor.dataclasses.orderbyid

import com.teamx.multivendor.dataclasses.Shop
import com.teamx.multivendor.dataclasses.allorders.Product

data class OrderByIdData(
    val __v: Int,
    val _id: String,
    val amount: Int,
    val billing_address: BillingAddress,
    val children: List<Any>,
    val createdAt: String,
    val customer: Customer,
    val customer_contact: String,
    val delivery_fee: Int,
    val delivery_time: String,
    val discount: Int,
    val paid_total: Double,
    val payment_gateway: String,
    val products: List<Product>,
    val sales_tax: Int,
    val shipping_address: ShippingAddress,
    val shop: Shop,
    val status: Status,
    val total: Int,
    val updatedAt: String
)