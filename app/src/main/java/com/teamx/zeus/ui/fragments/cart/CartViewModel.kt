package com.teamx.zeus.ui.fragments.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import com.teamx.zues.data.local.dbmodel.CartTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {


    private val _cartTableResponse = MutableLiveData<Resource<List<CartTable>>>()
    val cartResponse: LiveData<Resource<List<CartTable>>>
        get() = _cartTableResponse

    fun getCarts() {
        Log.d("TAG", "getCarts:1 ")
        viewModelScope.launch(Dispatchers.IO) {
            _cartTableResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getAllProducts2().let {
                        Log.d("TAG", "getCarts:2 ")
                        _cartTableResponse.postValue(Resource.success(it))

                    }
                } catch (e: Exception) {
                    Log.d("TAG", "getCarts:3${e.printStackTrace()} ")
                    _cartTableResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _cartTableResponse.postValue(
                Resource.error(
                    "No internet connection",
                    null
                )
            )
        }
    }

//    fun deleteCartProduct(Id: Int) {
//        Log.d("TAG", "getCarts:1 ")
//        viewModelScope.launch(Dispatchers.IO) {
//            if (networkHelper.isNetworkConnected()) {
//                try {
//                    mainRepository.deleteCartProduct(Id)
//                } catch (e: Exception) {
//                    Log.d("TAG", "getCarts:3${e.printStackTrace()} ")
//                }
//            } else {
//
//            }
//
//        }
//    }




}