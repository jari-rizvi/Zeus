package com.teamx.zues.data.local.dbmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teamx.zeus.data.models.productBySlug.ProductBySlugData

@Entity(tableName = "cart_table")
data class CartTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "product")
    val product: ProductBySlugData
)