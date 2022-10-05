package com.teamx.multivendor.dataclasses.feedback

import com.teamx.multivendor.dataclasses.Shop

data class Doc(
    val __v: Int,
    val _id: String,
    val abusive_reports_count: Int,
    val answer: String,
    val createdAt: String,
    val feedbacks: List<Any>,
    val negative_feedbacks_count: Int,
    val positive_feedbacks_count: Int,
    val product: Product,
    val question: String,
    val shop: Shop,
    val updatedAt: String,
    val user: User
)