package com.teamx.zeus.ui.fragments.forgotPass

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.google.gson.JsonObject
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.FragmentForgotPassBinding
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException

@AndroidEntryPoint
class ForgotPassFragment() : BaseFragment<FragmentForgotPassBinding, ForgotPassViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_forgot_pass
    override val viewModel: Class<ForgotPassViewModel>
        get() = ForgotPassViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private var userEmail: String? = null

    private lateinit var options: NavOptions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        options = navOptions {
            anim {
                enter = R.anim.enter_from_left
                exit = R.anim.exit_to_left
                popEnter = R.anim.nav_default_pop_enter_anim
                popExit = R.anim.nav_default_pop_exit_anim
            }
        }

        mViewDataBinding.btnSend.setOnClickListener {
            when {
                TextUtils.isEmpty(mViewDataBinding.userEmail.text.toString()) -> {
                    mViewDataBinding.userEmail.error = "Enter Email"
                    mViewDataBinding.userEmail.requestFocus()
                }

                else -> {
                    subscribeToNetworkLiveData()

                }

            }

        }

    }

    private fun initialization() {
        userEmail = mViewDataBinding.userEmail.getText().toString().trim()
    }

    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        initialization()

        if (!userEmail!!.isEmpty()) {

            val params = JsonObject()
            try {
                params.addProperty("email", userEmail)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            Log.d("UserData", params.toString())

            mViewModel.forgotPass(params)

            mViewModel.forgotPassResponse.observe(requireActivity(), Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->

                            val token = data.token
                            val email = data.email

                            val bundle = Bundle()
                            bundle.putString("email", email).toString()
                            bundle.putString("token", token).toString()

                            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                            navController.navigate(R.id.createNewPassFragment, bundle,options)
                        }
                    }
                    Resource.Status.ERROR -> {
                        loadingDialog.dismiss()
                        DialogHelperClass.errorDialog(requireContext(), it.message!!)
                    }
                }
            })
        }
    }

}