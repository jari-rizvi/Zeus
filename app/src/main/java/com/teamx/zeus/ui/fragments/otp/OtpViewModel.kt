package com.teamx.zeus.ui.fragments.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.models.SignUp.RegisterData
import com.teamx.zeus.data.models.otpVerify.OtpVerifyData
import com.teamx.zeus.data.models.resendOtp.ResendOtpData
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _otpVerifyResponse = MutableLiveData<Resource<OtpVerifyData>>()
    val otpVerifyResponse: LiveData<Resource<OtpVerifyData>>
        get() = _otpVerifyResponse

    fun otpVerify(param : JsonObject)
     {
        viewModelScope.launch {
            _otpVerifyResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.otpVerify(param) .let {
                        if (it.isSuccessful) {
                            _otpVerifyResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400 || it.code() == 422) {
                            val jsonObj = JSONObject(it.errorBody()!!.charStream().readText())
                            _otpVerifyResponse.postValue(Resource.error(jsonObj.getString("message")))
                        } else {
                            _otpVerifyResponse.postValue(Resource.error("Some thing went wrong", null))
                        }
                    }
                } catch (e: Exception) {
                    _otpVerifyResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _otpVerifyResponse.postValue(Resource.error("No internet connection", null))
        }
    }

    private val _resendOtpResponse = MutableLiveData<Resource<ResendOtpData>>()
    val resendOtpResponse: LiveData<Resource<ResendOtpData>>
        get() = _resendOtpResponse

    fun resendOtp(param : JsonObject) {
        viewModelScope.launch {
            _resendOtpResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.resendOtp(param) .let {
                        if (it.isSuccessful) {
                            _resendOtpResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400 || it.code() == 422) {
                            val jsonObj = JSONObject(it.errorBody()!!.charStream().readText())
                            _resendOtpResponse.postValue(Resource.error(jsonObj.getString("message")))
                        } else {
                            _resendOtpResponse.postValue(Resource.error("Some thing went wrong", null))
                        }
                    }
                } catch (e: Exception) {
                    _resendOtpResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _resendOtpResponse.postValue(Resource.error("No internet connection", null))
        }
    }

}