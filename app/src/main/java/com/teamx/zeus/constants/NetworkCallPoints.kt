package com.teamx.zeus.constants

import java.util.*

class NetworkCallPoints {
    companion object{
        const val LOGIN_PHONE ="token/phone";
        const val LOGIN ="token";
        const val SIGN_UP ="register/phone";
        const val HOME ="dashboard";
        const val OTP_VERIFY ="register/phone-verify";
        const val OTP_VERIFY_FORGOT ="verify-forget-password-token";
        const val RESEND_OTP_VERIFY ="register/resend-otp";
        const val RESET_PASS ="reset-password";
        const val FORGOT_PASS ="forget-password";
        const val SHOP_BY_SLUG ="shops/makeup-store";
        const val PRODUCTS_BY_ID ="products/{id}";
        const val PRODUCTS_BY_SLUG ="products/{slug}";
        const val Currency ="/settings/currency?currency=PKR";
        const val COUPOUNS ="coupons";


        const val PROFILE_USER = "me"
        const val UPLOAD_ATTACH = "attachments/images"


        //Notifications
        const val GET_USER_NOTIFICATIONS = "notifications"
        const val GET_READ_NOTIFICATIONS = "notifications/read"
        const val GET_UNREAD_NOTIFICATIONS = "notifications?unread=true"

        //Orders
        const val ORDER_LIST = "orders"
        const val GET_ORDER_BY_ID = "orders/{id}"


        //Reviews
        const val GET_ALL_REVIEWS = "reviews"


        var TOKENER = ""


    }

}