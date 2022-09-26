package com.teamx.zeus.data.remote


import com.google.gson.JsonObject
import com.teamx.zeus.constants.NetworkCallPoints
import com.teamx.zeus.data.models.Dashboard.DashboardResponse
import com.teamx.zeus.data.models.SignUp.RegisterData
import com.teamx.zeus.data.models.login.LoginData
import com.teamx.zeus.data.models.otpVerify.OtpVerifyData
import com.teamx.zeus.data.models.resendOtp.ResendOtpData
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(NetworkCallPoints.LOGIN)
    suspend fun login(@Body params: JsonObject?): Response<LoginData>

    @POST(NetworkCallPoints.LOGIN_PHONE)
    suspend fun loginPhone(@Body params: JsonObject?): Response<LoginData>

    @POST(NetworkCallPoints.SIGN_UP)
    suspend fun signup(@Body params: JsonObject?): Response<RegisterData>

    @POST(NetworkCallPoints.OTP_VERIFY)
    suspend fun otpVerify(@Body params: JsonObject?): Response<OtpVerifyData>

    @POST(NetworkCallPoints.RESEND_OTP_VERIFY)
    suspend fun resendOtp(@Body params: JsonObject?): Response<ResendOtpData>

    @GET(NetworkCallPoints.HOME)
    suspend fun home(): Response<DashboardResponse>


}