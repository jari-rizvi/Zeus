package com.teamx.zeus.data.remote


import com.google.gson.JsonObject
import com.teamx.multivendor.dataclasses.addreview.AddReviewData
import com.teamx.multivendor.dataclasses.allorders.AllOrdersData
import com.teamx.multivendor.dataclasses.allreviews.AllReviews
import com.teamx.zeus.constants.NetworkCallPoints
import com.teamx.zeus.constants.NetworkCallPoints.Companion.TOKENER
import com.teamx.zeus.data.models.Dashboard.DashboardResponse
import com.teamx.zeus.data.models.ResetPass.ResetPassData
import com.teamx.zeus.data.models.SignUp.RegisterData
import com.teamx.zeus.data.models.forgotPass.ForgotData
import com.teamx.zeus.data.models.otpVerify.OtpVerifyData
import com.teamx.zeus.data.models.otpVerifyForgot.OtpVerifyForgotData
import com.teamx.zeus.data.models.productBySlug.ProductBySlugData
import com.teamx.zeus.data.models.productsShop.ShopProductsData
import com.teamx.zeus.data.models.resendOtp.ResendOtpData
import com.teamx.zeus.data.models.shopBySlug.ShopBySlugData
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(NetworkCallPoints.LOGIN)
    suspend fun login(@Body params: JsonObject?): Response<com.teamx.zeus.data.models.login.LoginData>

    @POST(NetworkCallPoints.LOGIN_PHONE)
    suspend fun loginPhone(@Body params: JsonObject?): Response<com.teamx.zeus.data.models.login.LoginData>

    @POST(NetworkCallPoints.SIGN_UP)
    suspend fun signup(@Body params: JsonObject?): Response<RegisterData>

    @POST(NetworkCallPoints.OTP_VERIFY)
    suspend fun otpVerify(@Body params: JsonObject?): Response<OtpVerifyData>

    @POST(NetworkCallPoints.OTP_VERIFY_FORGOT)
    suspend fun otpVerifyForgot(@Body params: JsonObject?): Response<OtpVerifyForgotData>

    @POST(NetworkCallPoints.RESEND_OTP_VERIFY)
    suspend fun resendOtp(@Body params: JsonObject?): Response<ResendOtpData>

    @POST(NetworkCallPoints.FORGOT_PASS)
    suspend fun forgotPass(@Body params: JsonObject?): Response<ForgotData>

    @POST(NetworkCallPoints.RESET_PASS)
    suspend fun resetPass(@Body params: JsonObject?): Response<ResetPassData>

    @GET(NetworkCallPoints.HOME)
    suspend fun home(): Response<DashboardResponse>

    @GET(NetworkCallPoints.SHOP_BY_SLUG)
    suspend fun shopBySlug(): Response<ShopBySlugData>

    @GET(NetworkCallPoints.PRODUCTS_BY_ID)
    suspend fun productsByShop(): Response<ShopProductsData>

    @GET(NetworkCallPoints.PRODUCTS_BY_SLUG)
    suspend fun productsBySlug(): Response<ProductBySlugData>

    @GET(NetworkCallPoints.ORDER_LIST)
    suspend fun getOrders(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Header("Authorization") basicCredentials: String =
            "Bearer $TOKENER"
    ): Response<AllOrdersData>


    @GET(NetworkCallPoints.GET_ALL_REVIEWS)
    suspend fun getRatingList(
        /*       @Path("id") id: String,
               @Query("page") page: Int,
               @Query("limit") limit: Int,*/
        @Header("Authorization") basicCredentials: String =
            "Bearer $TOKENER" ?: ""
    ): Response<AllReviews>

}