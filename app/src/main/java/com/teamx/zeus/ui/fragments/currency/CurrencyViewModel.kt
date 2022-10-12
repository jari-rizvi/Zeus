package com.teamx.zeus.ui.fragments.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamx.zues.dataclasses.allorders.AllOrdersData
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.dataclasses.currency.CurrencyData
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _currencyListResponse = MutableLiveData<Resource<CurrencyData>>()
    val currencyListResponse: LiveData<Resource<CurrencyData>>
        get() = _currencyListResponse

    fun getCurrencyList() {
        viewModelScope.launch {
            _currencyListResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getCurrency().let {
                        if (it.isSuccessful) {
                            _currencyListResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                            _currencyListResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            _currencyListResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    null
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    _currencyListResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _currencyListResponse.postValue(Resource.error("No internet connection", null))
        }
    }


}