package com.teamx.zeus.ui.fragments.Coupons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.dataclasses.coupouns.CoupounData
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoupounViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _coupounResponse = MutableLiveData<Resource<CoupounData>>()
    val coupounResponse: LiveData<Resource<CoupounData>>
        get() = _coupounResponse

    fun getCoupoun() {
        viewModelScope.launch {
            _coupounResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getCoupun() .let {
                        if (it.isSuccessful) {
                            _coupounResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400) {
                            _coupounResponse.postValue(Resource.error(it.message(), null))
                        } else {
                            _coupounResponse.postValue(Resource.error("Some thing went wrong", null))
                        }
                    }
                } catch (e: Exception) {
                    _coupounResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _coupounResponse.postValue(Resource.error("No internet connection", null))
        }
    }

}