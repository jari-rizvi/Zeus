package com.teamx.zues.dataclasses.vendors

import com.teamx.zues.dataclasses.Address
import com.teamx.zues.dataclasses.Balance
import com.teamx.zues.dataclasses.Settings

import androidx.annotation.Keep


@Keep
data class Doc(
    val __v: Int,
    val _id: String,
    val address: Address,
    val balance: Balance,
    val cover_image: String,
    val createdAt: String,
    val description: String,
    val is_active: Boolean,
    val logo: String,
    val name: String,
    val orders_count: Int,
    val owner: Owner,
    val products_count: Int,
    val settings: Settings,
    val slug: String,
    val staffs: List<Any>,
    val updatedAt: String
)