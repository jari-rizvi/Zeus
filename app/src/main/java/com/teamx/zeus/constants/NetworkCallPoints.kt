package com.teamx.zeus.constants

class NetworkCallPoints {
    companion object{
        const val API_GET_ALL_PRODUCTS ="/products";
        const val LOGIN ="signin";
        const val SIGN_UP ="signup";
        const val HOME ="product/dashboard";
        const val FORGOT_PASS ="forgot-password";
        const val OTP ="otp-authenticate";
        const val Update_Pass ="reset-password";
        const val GET_PRODUCT_ID ="product/{id}";
        const val TOP_SELLING ="product/top-seller";
        const val PRODUCT_RATING ="getRatingByProductId";
        const val RATING_LIST ="product/rating/{id}";
        const val ORDER_LIST ="getOrders";
        const val ORDER_DETAIL ="getOrder";
        const val ADD_REVIEW ="add-review";
        const val GET_STORE_BY_ID ="vendor/get";
        const val GET_STORE_CATEGORIES ="product/vendor/byCategory";
        const val EDIT_PROFILE ="updateUser";
        const val GET_NOTIFICATIONS ="notifications/all";
    }
}