package com.teamx.zeus.ui.fragments.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import com.teamx.zues.dataclasses.orderbyid.OrderByIdData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

    fun logOutUser() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.deleteUserData()
            mainRepository.deleteAllCartItems()
        }
    }

}