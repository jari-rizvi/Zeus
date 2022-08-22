package com.teamx.zeus.ui.fragments.SignInFragment

import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.remote.reporitory.MainRepository
import com.teamx.zeus.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {



}