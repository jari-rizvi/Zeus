package com.teamx.multivendor.dataclasses.addreview

data class AddReviewData(
    val comment: String,
    val photos: List<String>,
    val pivotId: String,
    val product: String,
    val rating: Int,
    val shop: String
)