package com.teamx.zeus.dummyData

import com.teamx.zeus.data.models.productBySlug.ProductBySlugData

class Cart(
     val id: Int,
     val name: String,
     val modifier: String,
     val price: Double = 0.0,
     val imageUrl: String,
     var quantity: Int = 1,
     var productBySlug: ProductBySlugData? = null
)
