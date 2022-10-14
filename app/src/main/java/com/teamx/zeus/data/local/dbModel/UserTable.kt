package com.teamx.zeus.data.local.dbModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.teamx.zues.dataclasses.login.LoginData


@Entity(
    tableName = "user_table",
    indices = [Index(value = ["user_data"], unique = true)]
)
data class UserTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "user_data")
    val user_data: LoginData
)