package com.teamx.zeus.ui.fragments.SignInFragment

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
import com.teamx.zeus.localization.LocaleManager
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException


@AndroidEntryPoint
class SignInFragment() : BaseFragment<FragmentSignInBinding, AuthViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_sign_in
    override val viewModel: Class<AuthViewModel>
        get() = AuthViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private var userEmail: String? = null
    private var password: String? = null
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var options: NavOptions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (!MainApplication.localeManager!!.getLanguage().equals(LocaleManager.Companion.LANGUAGE_ENGLISH)) {

            lang = LocaleManager.LANGUAGE_ARABIC

        } else {
            lang = LocaleManager.LANGUAGE_ENGLISH
        }

        /*  mViewDataBinding.btnRegister.setOnClickListener {
            naviagteFragment(R.id.signUpFragment,true)
        }*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        options = navOptions {
            anim {
                enter = R.anim.enter_from_left
                exit = R.anim.exit_to_left
                popEnter = R.anim.nav_default_pop_enter_anim
                popExit = R.anim.nav_default_pop_exit_anim
            }
        }

        mViewDataBinding.btnGoogle.setOnClickListener {
            getMainActivity()!!.googleSignIn()
        }

        mViewDataBinding.btnRegister.setOnClickListener {

            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.signUpFragment, null, options)

        }

        mViewDataBinding.btnForgot.setOnClickListener {

            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.forgetPasswordFragment, null, options)

        }

        mViewDataBinding.btnLogin.setOnClickListener {
            when {
                TextUtils.isEmpty(mViewDataBinding.editEmailPhone.text.toString()) -> {
                    mViewDataBinding.editEmailPhone.error = "Enter Email"
                    mViewDataBinding.editEmailPhone.requestFocus()
                }

                TextUtils.isEmpty(mViewDataBinding.editPassword.text.toString()) -> {
                    mViewDataBinding.editPassword.error = "Enter Password"
                    mViewDataBinding.editPassword.requestFocus()

                }
                else -> {
                    subscribeToNetworkLiveData()
                }
            }
        }
    }

    private fun initialization() {
        userEmail = mViewDataBinding.editEmailPhone.getText().toString().trim()
        password = mViewDataBinding.editPassword.getText().toString().trim()
    }


    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        initialization()

        if (!userEmail!!.isEmpty() || !password!!.isEmpty()) {

            val params = JsonObject()
            try {
                params.addProperty("user", userEmail)
                params.addProperty("password", password)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            Log.e("UserData", params.toString())

            mViewModel.login(params)

            mViewModel.loginResponse.observe(requireActivity(), Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->
                            if (data.flag == 1) {
                                lifecycleScope.launch(Dispatchers.IO){
                                    dataStoreProvider.saveUserToken(data.token)
                                    dataStoreProvider.saveUserDetails(data.user.firstName.toString(),data.user.email)

                                }

                                navController = Navigation.findNavController(
                                    requireActivity(),
                                    R.id.nav_host_fragment
                                )
                                navController.navigate(R.id.homeFragment2, null, options)
                            } else {
                                showToast(data.message)
                            }
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