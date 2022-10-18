package com.teamx.zeus.data.remote.reporitory

import com.google.gson.JsonObject
import com.teamx.zeus.data.local.db.AppDao
import com.teamx.zeus.data.remote.ApiService
import com.teamx.zeus.data.local.dbModel.CartDao
import com.teamx.zeus.data.local.dbModel.CartTable
import com.teamx.zeus.data.local.dbModel.UserDao
import com.teamx.zeus.data.local.dbModel.UserTable
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao,
    var localDataSource2: CartDao
//    var localDataSource4: UserDao
) {
    suspend fun login(@Body param: JsonObject) = apiService.login(param)

    suspend fun loginPhone(@Body param: JsonObject) = apiService.loginPhone(param)

    suspend fun signup(@Body param: JsonObject) = apiService.signup(param)

    suspend fun otpVerify(@Body param: JsonObject) = apiService.otpVerify(param)

    suspend fun otpVerifyForgot(@Body param: JsonObject) = apiService.otpVerifyForgot(param)

    suspend fun resendOtp(@Body param: JsonObject) = apiService.resendOtp(param)

    suspend fun forogtPass(@Body param: JsonObject) = apiService.forgotPass(param)

    suspend fun resetPass(@Body param: JsonObject) = apiService.resetPass(param)

    suspend fun home() = apiService.home()

    suspend fun shopBySlug() = apiService.shopBySlug()

    suspend fun productsByShop() = apiService.productsByShop()

    suspend fun productsBySlug(@Path("slug") slug: String) = apiService.productsBySlug(slug)

    suspend fun getRatingList() = apiService.getRatingList(/*id, page, limit*/)

    suspend fun getOrderList(@Query("page") page: Int, @Query("limit") limit: Int) =
        apiService.getOrders(page, limit)

    suspend fun getOrderDetail(@Query("id") id: String) = apiService.getOrderDetail(id)

    suspend fun placeReview(param: JsonObject) = apiService.placeReview(params = param)

    suspend fun editProfile(/*@Query("id") id: String, @Body param: JsonObject*/) =
        apiService.editProfile(/*id,param*/)

    suspend fun updateProfile(@Body param: JsonObject) = apiService.updateProfile(param)

    suspend fun updateImgProfile(param: MultipartBody.Part) = apiService.uploadAttachment(param)

    suspend fun getCurrency() =
        apiService.getCurrency()

    suspend fun getCoupun() =
        apiService.getCoupoun()

    suspend fun getNotifications(@Query("user") user: String,/* @Query("limit") limit: Int*/) =
        apiService.getNotifications(user=user/*page, limit*/)

    suspend fun getReadNotifications() = apiService.getReadNotifications()
    suspend fun getUnreadNotifications(@Query("page") page: Int, @Query("limit") limit: Int) =
        apiService.getUnreadNotifications(page, limit)


    // Local Database
    fun getAllProducts2(): List<CartTable> = localDataSource2.getAllProducts2()

    suspend fun insertCartProduct(cartTable: CartTable) = localDataSource2.insert(cartTable)
//    suspend fun insertUserDetails(userTable: UserTable) = localDataSource4.insert(userTable)

//    suspend fun deleteUserData() = localDataSource4.deleteUser()
    suspend fun deleteAllCartItems() = localDataSource2.deleteAllCart()


}