package com.teamx.zeus.data.dataclasses.currency

import androidx.annotation.Keep


@Keep
data class CurrencyModel(
    val name: String,
    val rate: Double,
    var isChecked : Boolean

)