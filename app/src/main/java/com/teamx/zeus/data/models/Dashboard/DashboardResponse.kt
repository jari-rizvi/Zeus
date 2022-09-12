package com.teamx.zeus.data.models.Dashboard

data class DashboardResponse(
    val categories: List<Category>,
    val dashboard: Dashboard,
    val popularProducts: List<PopularProduct>,
    val popularShops: List<PopularShop>,
    val topRatedProducts: List<TopRatedProduct>
)