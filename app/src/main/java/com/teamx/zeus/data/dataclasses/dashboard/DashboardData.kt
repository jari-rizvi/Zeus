package com.teamx.zues.dataclasses.dashboard

import androidx.annotation.Keep

@Keep
data class DashboardData(
    val categories: List<Category>,
    val dashboard: Dashboard,
    val popularProducts: List<PopularProduct>,
    val popularShops: List<PopularShop>,
    val topRatedProducts: List<TopRatedProduct>
)