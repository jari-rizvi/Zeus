package com.teamx.zeus.ui.fragments.addReview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.teamx.zues.dataclasses.addreview.AddReviewData
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class AddReviewViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    private val _addReviewResponse = MutableLiveData<Resource<AddReviewData>>()
    val addReviewResponse: LiveData<Resource<AddReviewData>>
        get() = _addReviewResponse

    fun addReview(param: JsonObject) {
        viewModelScope.launch {
            _addReviewResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.placeReview(param).let {
                        if (it.isSuccessful) {
                            _addReviewResponse.postValue(Resource.success(it.body()!!))
                        } else if (it.code() == 500 || it.code() == 404 || it.code() == 400 || it.code() == 403) {
                            val jsonObj = JSONObject(it.errorBody()!!.charStream().readText())
                            _addReviewResponse.postValue(Resource.error(jsonObj.getString("message")))
                        } else {
                            _addReviewResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    it.body()
                                )
                            )
                        }
                    }

                } catch (e: Exception) {
                    _addReviewResponse.postValue(Resource.error("${e.message}"))
                }
            } else _addReviewResponse.postValue(Resource.error("No internet connection", null))
        }
    }

}