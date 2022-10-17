package com.teamx.zeus.ui.fragments.notification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import com.teamx.zues.dataclasses.notification.NotificationData
import com.teamx.zues.dataclasses.readallnotification.ReadAllNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationListViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _notificationListResponse = MutableLiveData<Resource<NotificationData>>()
    val notificationListResponse: LiveData<Resource<NotificationData>>
        get() = _notificationListResponse

    private val _notificationUnreadListResponse = MutableLiveData<Resource<NotificationData>>()
    val notificationUnreadListResponse: LiveData<Resource<NotificationData>>
        get() = _notificationUnreadListResponse

    private val _notificationReadListResponse = MutableLiveData<Resource<ReadAllNotification>>()
    val notificationReadListResponse: LiveData<Resource<ReadAllNotification>>
        get() = _notificationReadListResponse

    fun getNotification(user:String/*page: Int, limit: Int*/) {
        Log.d("TAG", "getNotification1: ")
        viewModelScope.launch {
            _notificationListResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getNotifications(/*page, limit*/user).let {
                        if (it.isSuccessful) {
                            Log.d("TAG", "getNotification22${it.code()}: ")
                            _notificationListResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {

                            Log.d("TAG", "getNotification2${it.code()}: ")
                            _notificationListResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            Log.d("TAG", "getNotification3${it.code()}: ")
                            _notificationListResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    null
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    _notificationListResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _notificationListResponse.postValue(
                Resource.error(
                    "No internet connection",
                    null
                )
            )
        }
    }

    fun getReadNotification() {
        Log.d("TAG", "getNotification: ")
        viewModelScope.launch {
            _notificationReadListResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getReadNotifications().let {
                        if (it.isSuccessful) {
                            _notificationReadListResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {

                            Log.d("TAG", "getNotification${it.code()}: ")
                            _notificationReadListResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            Log.d("TAG", "getNotification${it.code()}: ")
                            _notificationReadListResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    null
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    _notificationReadListResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _notificationReadListResponse.postValue(
                Resource.error(
                    "No internet connection",
                    null
                )
            )
        }
    }

    fun getUnreadNotification(page: Int, limit: Int) {
        Log.d("TAG", "getNotification: ")
        viewModelScope.launch {
            _notificationUnreadListResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getUnreadNotifications(page, limit).let {
                        if (it.isSuccessful) {
                            _notificationUnreadListResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {

                            Log.d("TAG", "getNotification${it.code()}: ")
                            _notificationUnreadListResponse.postValue(
                                Resource.error(
                                    it.message(),
                                    null
                                )
                            )
                        } else {
                            Log.d("TAG", "getNotification${it.code()}: ")
                            _notificationListResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    null
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    _notificationListResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _notificationListResponse.postValue(
                Resource.error(
                    "No internet connection",
                    null
                )
            )
        }
    }


}