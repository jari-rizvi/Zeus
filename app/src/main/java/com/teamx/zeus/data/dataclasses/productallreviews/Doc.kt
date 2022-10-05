package com.teamx.multivendor.dataclasses.productallreviews

import com.teamx.multivendor.dataclasses.Shop

data class Doc(
    val __v: Int,
    val _id: String,
    val abusive_reports_count: Int,
    val comment: String,
    val createdAt: String,
    val feedbacks: List<Any>,
    val negative_feedbacks_count: Int,
    val photos: List<Any>,
    val positive_feedbacks_count: Int,
    val product: Product,
    val rating: Int,
    val shop: Shop,
    val updatedAt: String,
    val user: User
)