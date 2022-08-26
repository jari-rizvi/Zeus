package com.teamx.zeus.ui.fragments.Signup

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
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
import com.teamx.zeus.databinding.FragmentSignInBinding
import com.teamx.zeus.databinding.FragmentSignUpBinding
import com.teamx.zeus.localization.LocaleManager
import com.teamx.zeus.ui.fragments.SignInFragment.AuthViewModel
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException


@AndroidEntryPoint
class SignUpFragment() : BaseFragment<FragmentSignUpBinding, SignupViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_sign_up
    override val viewModel: Class<SignupViewModel>
        get() = SignupViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    private lateinit var options: NavOptions

    private var userEmail: String? = null
    private var password: String? = null
    private var name: String? = null
    private var userNumber: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

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

        mViewDataBinding.btnSignup.setOnClickListener {
            when {
                TextUtils.isEmpty(mViewDataBinding.userName.text.toString()) -> {
                    mViewDataBinding.userName.error = "Enter First Name"
                    mViewDataBinding.userName.requestFocus()

                }
                TextUtils.isEmpty(mViewDataBinding.userEmail.text.toString()) -> {
                    mViewDataBinding.userEmail.error = "Enter Email"
                    mViewDataBinding.userEmail.requestFocus()

                }
                TextUtils.isEmpty(mViewDataBinding.userPass.text.toString()) -> {
                    mViewDataBinding.userPass.error = "Enter Passwpord"
                    mViewDataBinding.userPass.requestFocus()

                }

                TextUtils.isEmpty(mViewDataBinding.userPhone.text.toString()) -> {
                    mViewDataBinding.userPhone.error = "Enter Phone"
                    mViewDataBinding.userPhone.requestFocus()
                }

                else -> {
                    subscribeToNetworkLiveData()
                }
            }

        }





    }

    private fun initialization() {
        name = mViewDataBinding.userName.getText().toString().trim()
        userEmail = mViewDataBinding.userEmail.getText().toString().trim()
        password = mViewDataBinding.userPass.getText().toString().trim()
        userNumber = mViewDataBinding.userPhone.getText().toString().trim()

    }


    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        initialization()

        if (!userEmail!!.isEmpty() || !password!!.isEmpty()) {

            val params = JsonObject()
            try {
                params.addProperty("name", name.toString())
                params.addProperty("email", userEmail.toString())
                params.addProperty("phone", userNumber.toString())
                params.addProperty("password", password.toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            Log.e("UserData", params.toString())



            mViewModel.signup(params)

            mViewModel.signupResponse.observe(requireActivity(), Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->
                            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                            navController.navigate(R.id.otpFragment, null,options)
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