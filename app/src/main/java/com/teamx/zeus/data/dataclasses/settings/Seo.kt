package com.teamx.multivendor.dataclasses.settings

data class Seo(
    val _id: String,
    val canonicalUrl: String,
    val metaDescription: String,
    val metaTags: String,
    val metaTitle: String,
    val ogDescription: String,
    val ogImage: String,
    val ogTitle: String,
    val twitterCardType: String,
    val twitterHandle: String
)