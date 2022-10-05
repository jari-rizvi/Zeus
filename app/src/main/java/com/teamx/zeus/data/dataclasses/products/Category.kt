package com.teamx.multivendor.dataclasses.products

data class Category(
    val __v: Int,
    val _id: String,
    val children: List<Any>,
    val createdAt: String,
    val details: String,
    val icon: String,
    val name: String,
    val products: List<Any>,
    val slug: String,
    val type: String,
    val updatedAt: String
)