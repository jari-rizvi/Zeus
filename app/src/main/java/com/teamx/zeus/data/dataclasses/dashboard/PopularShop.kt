package com.teamx.zues.dataclasses.dashboard

import com.teamx.zues.dataclasses.Address

import androidx.annotation.Keep


@Keep
data class PopularShop(
    val __v: Int,
    val _id: String,
    val address: Address,
    val balance: BalanceX,
    val cover_image: String,
    val createdAt: String,
    val description: String,
    val is_active: Boolean,
    val logo: String,
    val name: String,
    val orders_count: Int,
    val owner: String,
    val products_count: Int,
    val settings: Settings,
    val slug: String,
    val staffs: List<Any>,
    val updatedAt: String
)