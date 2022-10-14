package com.teamx.zeus.data.local.dbModel

import androidx.room.*
import com.teamx.zeus.data.local.dbModel.UserTable
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getUserDetailsLive(): Flow<List<UserTable>>

    @Query("SELECT * FROM user_table")
    fun getUserDetails(): List<UserTable>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: UserTable)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(product: UserTable)

    @Query("DELETE FROM user_table")
    suspend fun deleteUser()
}