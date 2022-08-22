package com.teamx.zeus.ui.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.models.SignIn.SignInResponse
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {


    private val _loginResponse = MutableLiveData<Resource<SignInResponse>>()
    val loginResponse: LiveData<Resource<SignInResponse>>
        get() = _loginResponse

    fun login(param : JsonObject) {
        viewModelScope.launch {
            _loginResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.login(param) .let {
                        if (it.isSuccessful) {
                            _loginResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                            _loginResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            _loginResponse.postValue(Resource.error("Some thing went wrong", null))
                        }
                    }
                } catch (e: Exception) {
                    _loginResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _loginResponse.postValue(Resource.error("No internet connection", null))
        }
    }



}