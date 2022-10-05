package com.teamx.zeus.data.remote.reporitory

import com.google.gson.JsonObject
import com.teamx.zeus.data.local.db.AppDao
import com.teamx.zeus.data.remote.ApiService
import com.teamx.zues.data.local.dbmodel.CartDao
import com.teamx.zues.data.local.dbmodel.CartTable
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao,
    var localDataSource2: CartDao

    ) {
    suspend fun login(@Body param : JsonObject) = apiService.login(param)

    suspend fun loginPhone(@Body param : JsonObject) = apiService.loginPhone(param)

    suspend fun signup(@Body param : JsonObject) = apiService.signup(param)

    suspend fun otpVerify(@Body param : JsonObject) = apiService.otpVerify(param)

    suspend fun otpVerifyForgot(@Body param : JsonObject) = apiService.otpVerifyForgot(param)

    suspend fun resendOtp(@Body param : JsonObject) = apiService.resendOtp(param)

    suspend fun forogtPass(@Body param : JsonObject) = apiService.forgotPass(param)

    suspend fun resetPass(@Body param : JsonObject) = apiService.resetPass(param)

    suspend fun home() = apiService.home()

    suspend fun shopBySlug() = apiService.shopBySlug()

    suspend fun productsByShop() = apiService.productsByShop()

    suspend fun productsBySlug() = apiService.productsBySlug()

    suspend fun getRatingList(
        /*    @Path("id") id: String,
            @Query("page") page: Int,
            @Query("limit") limit: Int*/
    ) = apiService.getRatingList(/*id, page, limit*/)
    suspend fun getOrderList(@Query("page") page: Int, @Query("limit") limit: Int) =
        apiService.getOrders(page, limit)


// Local Database
    fun getAllProducts2(): List<CartTable> = localDataSource2.getAllProducts2()

    suspend fun insertCartProduct(cartTable: CartTable) = localDataSource2.insert(cartTable)



}