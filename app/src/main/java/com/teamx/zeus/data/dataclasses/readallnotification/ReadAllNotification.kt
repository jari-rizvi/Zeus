package com.teamx.multivendor.dataclasses.readallnotification

data class ReadAllNotification(
    val acknowledged: Boolean,
    val matchedCount: Int,
    val modifiedCount: Int,
    val upsertedCount: Int,
    val upsertedId: Any
)