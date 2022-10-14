package com.teamx.zues.ui.fragments.OrderDeatilsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import com.teamx.zues.dataclasses.orderbyid.OrderByIdData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _orderDetailResponse = MutableLiveData<Resource<OrderByIdData>>()
    val orderDetailResponse: LiveData<Resource<OrderByIdData>>
        get() = _orderDetailResponse

    fun orderDetail(id: String) {
        viewModelScope.launch {
            _orderDetailResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getOrderDetail(id).let {
                        if (it.isSuccessful) {
                            _orderDetailResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
//                            _loginResponse.postValue(Resource.error(it.message(), null))
                            val jsonObj = JSONObject(it.errorBody()!!.charStream().readText())
                            _orderDetailResponse.postValue(Resource.error(jsonObj.getString("message")))
                        } else {
                            _orderDetailResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    it.body()
                                )
                            )
                        }
                    }

                } catch (e: Exception) {
                    _orderDetailResponse.postValue(Resource.error("${e.message}"))
                }
            } else _orderDetailResponse.postValue(Resource.error("No internet connection", null))
        }
    }

}