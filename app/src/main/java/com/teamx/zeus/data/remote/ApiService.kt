package com.teamx.zeus.data.remote


import com.google.gson.JsonObject
import com.teamx.zues.dataclasses.addreview.AddReviewData
import com.teamx.zues.dataclasses.allorders.AllOrdersData
import com.teamx.zues.dataclasses.allreviews.AllReviews
import com.teamx.zues.dataclasses.dashboard.DashboardData
import com.teamx.zeus.constants.NetworkCallPoints
import com.teamx.zeus.constants.NetworkCallPoints.Companion.TOKENER
import com.teamx.zeus.data.dataclasses.coupouns.CoupounData
import com.teamx.zeus.data.dataclasses.currency.CurrencyData
import com.teamx.zeus.data.models.ResetPass.ResetPassData
import com.teamx.zeus.data.models.SignUp.RegisterData
import com.teamx.zeus.data.models.forgotPass.ForgotData
import com.teamx.zeus.data.models.otpVerify.OtpVerifyData
import com.teamx.zeus.data.models.otpVerifyForgot.OtpVerifyForgotData
import com.teamx.zeus.data.models.productBySlug.ProductBySlugData
import com.teamx.zeus.data.models.productsShop.ShopProductsData
import com.teamx.zeus.data.models.resendOtp.ResendOtpData
import com.teamx.zeus.data.models.shopBySlug.ShopBySlugData
import com.teamx.zues.dataclasses.login.LoginData
import com.teamx.zues.dataclasses.notification.NotificationData
import com.teamx.zues.dataclasses.orderbyid.OrderByIdData
import com.teamx.zues.dataclasses.profile.ProfileData
import com.teamx.zues.dataclasses.profile.ProfileDataX
import com.teamx.zues.dataclasses.profile.UploadModelData
import com.teamx.zues.dataclasses.readallnotification.ReadAllNotification
import okhttp3.MultipartBody
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

    @POST(NetworkCallPoints.OTP_VERIFY_FORGOT)
    suspend fun otpVerifyForgot(@Body params: JsonObject?): Response<OtpVerifyForgotData>

    @POST(NetworkCallPoints.RESEND_OTP_VERIFY)
    suspend fun resendOtp(@Body params: JsonObject?): Response<ResendOtpData>

    @POST(NetworkCallPoints.FORGOT_PASS)
    suspend fun forgotPass(@Body params: JsonObject?): Response<ForgotData>

    @POST(NetworkCallPoints.RESET_PASS)
    suspend fun resetPass(@Body params: JsonObject?): Response<ResetPassData>

    @GET(NetworkCallPoints.HOME)
    suspend fun home(): Response<DashboardData>

    @GET(NetworkCallPoints.SHOP_BY_SLUG)
    suspend fun shopBySlug(): Response<ShopBySlugData>

    @GET(NetworkCallPoints.PRODUCTS_BY_ID)
    suspend fun productsByShop(): Response<ShopProductsData>

    @GET(NetworkCallPoints.PRODUCTS_BY_SLUG)
    suspend fun productsBySlug(
        @Path("slug") slug: String,
        @Header("Authorization") basicCredentials: String = "Bearer $TOKENER" ?: ""
    ): Response<ProductBySlugData>

    @GET(NetworkCallPoints.ORDER_LIST)
    suspend fun getOrders(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Header("Authorization") basicCredentials: String =
            "Bearer $TOKENER"
    ): Response<AllOrdersData>

    @GET(NetworkCallPoints.GET_ORDER_BY_ID)
    suspend fun getOrderDetail(
        @Path("id") id: String,
        @Header("Authorization") basicCredentials: String =
            "Bearer $TOKENER" ?: ""
    ): Response<OrderByIdData>

    @GET(NetworkCallPoints.Currency)
    suspend fun getCurrency(): Response<CurrencyData>

    @GET(NetworkCallPoints.COUPOUNS)
    suspend fun getCoupoun(): Response<CoupounData>


    @GET(NetworkCallPoints.GET_ALL_REVIEWS)
    suspend fun getRatingList(
        /*       @Path("id") id: String,
               @Query("page") page: Int,
               @Query("limit") limit: Int,*/
        @Header("Authorization") basicCredentials: String =
            "Bearer $TOKENER" ?: ""
    ): Response<AllReviews>

    @POST(NetworkCallPoints.GET_ALL_REVIEWS)
    suspend fun placeReview(
        @Header("Authorization") basicCredentials: String = "Bearer $TOKENER" ?: "",
        @Body params: JsonObject?
//        @Header("Authorization") basicCredentials: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InVzYW1hQHRlYW14Lmdsb2JhbCIsInJvbGVzIjpbImN1c3RvbWVyIl0sImlhdCI6MTY2MzE3MzQ3NywiZXhwIjoxNjYzNzc4Mjc3fQ.X_YTIys9MlZbdCqfUz2qu9gkW5Zfsyq7Q3SvvjvuC4Y"
    ): Response<AddReviewData>

    @GET(NetworkCallPoints.PROFILE_USER)
    suspend fun editProfile(
        @Header("Authorization") basicCredentials: String = "Bearer $TOKENER" ?: ""
    ): Response<ProfileDataX>

    @PUT(NetworkCallPoints.PROFILE_USER)
    suspend fun updateProfile(
        @Body params: JsonObject?,
        @Header("Authorization") basicCredentials: String = "Bearer $TOKENER" ?: ""
    ): Response<ProfileData>


    @GET(NetworkCallPoints.GET_USER_NOTIFICATIONS)
    suspend fun getNotifications(
        @Query("user") user: String,
/*        @Query("page") page: Int,
        @Query("limit") limit: Int,*/
        @Header("Authorization") basicCredentials: String =
            "Bearer $TOKENER" ?: ""
    ): Response<NotificationData>

    @GET(NetworkCallPoints.GET_READ_NOTIFICATIONS)
    suspend fun getReadNotifications(
        @Header("Authorization") basicCredentials: String =
            "Bearer $TOKENER" ?: ""

    ): Response<ReadAllNotification>


    @GET(NetworkCallPoints.GET_UNREAD_NOTIFICATIONS)
    suspend fun getUnreadNotifications(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Header("Authorization") basicCredentials: String =
            "Bearer $TOKENER" ?: ""
    ): Response<NotificationData>


//    @Multipart
//    @POST(NetworkCallPoints.UPLOAD_ATTACH)
//    suspend fun uploadAttachment(@Part filePart: MultipartBody.Part): Response<UploadModelData>

    @Multipart
    @POST(NetworkCallPoints.UPLOAD_ATTACH)
    suspend fun uploadAttachment(@Part filePart: MultipartBody.Part): Response<UploadModelData>



}