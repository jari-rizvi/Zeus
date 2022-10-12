package com.teamx.zeus.ui.fragments.OrderList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamx.zues.dataclasses.allorders.AllOrdersData
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _orderListResponse = MutableLiveData<Resource<AllOrdersData>>()
    val orderListResponse: LiveData<Resource<AllOrdersData>>
        get() = _orderListResponse

    fun getOrderList(page: Int, limit: Int) {
        viewModelScope.launch {
            _orderListResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getOrderList(page, limit).let {
                        if (it.isSuccessful) {
                            _orderListResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                            _orderListResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            _orderListResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    null
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    _orderListResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _orderListResponse.postValue(Resource.error("No internet connection", null))
        }
    }


}