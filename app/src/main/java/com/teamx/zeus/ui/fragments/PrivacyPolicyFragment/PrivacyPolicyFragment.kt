package com.teamx.zues.ui.fragments.PrivacyPolicyFragment

import android.os.Bundle
import android.view.View
import com.teamx.zeus.R
import com.teamx.zeus.BR
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.databinding.FragmentPrivacyPolicyBinding
import com.teamx.zeus.ui.fragments.SignInFragment.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrivacyPolicyFragment : BaseFragment<FragmentPrivacyPolicyBinding, AuthViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_privacy_policy
    override val viewModel: Class<AuthViewModel>
        get() = AuthViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewDataBinding.btnBack.setOnClickListener {

            popUpStack()
        }

    }

}