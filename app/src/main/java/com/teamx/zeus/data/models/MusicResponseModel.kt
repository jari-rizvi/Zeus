package com.teamx.zeus.data.models

data class MusicResponseModel(
    val resultCount: Int,
    val results: List<MusicModel>?,
)