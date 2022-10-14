package com.teamx.zeus.ui.fragments.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.models.productBySlug.ProductBySlugData
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import com.teamx.zeus.data.local.dbModel.CartTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class ProductPreviewViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _productPreviewResponse = MutableLiveData<Resource<ProductBySlugData>>()
    val productPreviewResponse: LiveData<Resource<ProductBySlugData>>
        get() = _productPreviewResponse

    fun productPreview() {
        viewModelScope.launch {
            _productPreviewResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.productsBySlug().let {
                        if (it.isSuccessful) {
                            _productPreviewResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400 || it.code() == 422) {
                            val jsonObj = JSONObject(it.errorBody()!!.charStream().readText())
                            _productPreviewResponse.postValue(Resource.error(jsonObj.getString("message")))
                        } else {
                            _productPreviewResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    null
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    _productPreviewResponse.postValue(Resource.error("${e.message}", null))
                }
            } else _productPreviewResponse.postValue(Resource.error("No internet connection", null))
        }
    }

    fun insertCartProduct(cartTable: CartTable) {
        Log.d("TAG", "getCarts:1 ")
        viewModelScope.launch(Dispatchers.IO) {
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.insertCartProduct(cartTable)
                } catch (e: Exception) {
                    Log.d("TAG", "getCarts:3${e.printStackTrace()} ")
                }
            } else {

            }

        }
    }


}