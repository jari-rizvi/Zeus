package com.teamx.zeus.data.remote.reporitory

import com.google.gson.JsonObject
import com.teamx.zeus.data.local.db.AppDao
import com.teamx.zeus.data.remote.ApiService
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao
) {
    suspend fun login(@Body param : JsonObject) = apiService.login(param)
    suspend fun signup(@Body param : JsonObject) = apiService.signup(param)
    suspend fun home() = apiService.home()



}