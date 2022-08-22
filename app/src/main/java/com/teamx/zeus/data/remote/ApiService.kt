package com.teamx.zeus.data.remote


import com.google.gson.JsonObject
import com.teamx.zeus.constants.NetworkCallPoints
import com.teamx.zeus.data.models.SignIn.SignInResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(NetworkCallPoints.LOGIN)
    suspend fun login(@Body params: JsonObject?): Response<SignInResponse>
}