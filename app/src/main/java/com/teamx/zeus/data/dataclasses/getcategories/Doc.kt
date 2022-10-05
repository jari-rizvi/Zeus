package com.teamx.multivendor.dataclasses.getcategories

data class Doc(
    val __v: Int,
    val _id: String,
    val children: List<Children>,
    val createdAt: String,
    val details: String,
    val icon: String,
    val name: String,
    val products: List<Any>,
    val slug: String,
    val type: String,
    val updatedAt: String
)