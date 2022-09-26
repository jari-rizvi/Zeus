package com.teamx.zeus.ui.fragments.otp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.gson.JsonObject
import com.teamx.zeus.BR
import com.teamx.zeus.MainApplication
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.FragmentForgotPassBinding
import com.teamx.zeus.databinding.FragmentOTPBinding
import com.teamx.zeus.databinding.FragmentSignInBinding
import com.teamx.zeus.localization.LocaleManager
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException


@AndroidEntryPoint
class OtpFragment() : BaseFragment<FragmentOTPBinding, OtpViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_o_t_p
    override val viewModel: Class<OtpViewModel>
        get() = OtpViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private var phoneNumber: String? = null
    private var sid: String? = null
    private var otpid: String? = null
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

        mViewDataBinding.btnVerify.setOnClickListener {

            verifyotp()
        }

        mViewDataBinding.resentOtp.setOnClickListener {
            resendOtp()

        }
    }

    fun initialization() {
        val bundle = arguments
        if (bundle != null) {
            phoneNumber = bundle.getString("phone").toString()
            sid = bundle.getString("Sid").toString()
            otpid = bundle.getString("otpid")

        }
    }

    fun verifyotp() {
        initialization()

        val code = mViewDataBinding.pinView.text.toString()
        if (sid!!.isNotEmpty() || otpid!!.isNotEmpty() || phoneNumber!!.isNotEmpty()) {
            val params = JsonObject()
            try {
                params.addProperty("sid", sid.toString())
                params.addProperty("otp_id", otpid.toString())
                params.addProperty("phone_number", phoneNumber.toString())
                params.addProperty("code", code)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            mViewModel.otpVerify(params)

            mViewModel.otpVerifyResponse.observe(requireActivity(), Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->

                            navController = Navigation.findNavController(
                                requireActivity(),
                                R.id.nav_host_fragment
                            )
                            navController.navigate(R.id.createNewPassFragment, null, options)

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

    fun resendOtp() {

        initialization()

        if (phoneNumber!!.isNotEmpty()) {
            val params = JsonObject()
            try {
                params.addProperty("contact", phoneNumber)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            mViewModel.resendOtp(params)

            mViewModel.resendOtpResponse.observe(requireActivity(), Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->
                            showToast(data.message)

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
