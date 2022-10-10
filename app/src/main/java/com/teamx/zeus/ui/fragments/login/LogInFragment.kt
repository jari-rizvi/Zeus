package com.teamx.zeus.ui.fragments.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.google.gson.JsonObject
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.FragmentLogInBinding
import com.teamx.zeus.utils.DialogHelperClass
import com.teamx.zeus.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import java.util.regex.Pattern


@AndroidEntryPoint
class LogInFragment() : BaseFragment<FragmentLogInBinding, LoginViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_log_in
    override val viewModel: Class<LoginViewModel>
        get() = LoginViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private var userEmail: String? = null
    private var password: String? = null

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


        mViewDataBinding.btnRegister.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.signUpFragment, null, options)
        }

        mViewDataBinding.btnForgot.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.forgotPassFragment, null, options)
        }

        mViewDataBinding.btnLogin.setOnClickListener {
            isValidate()
            }

    }

    private fun initialization() {
        userEmail = mViewDataBinding.userEmail.getText().toString().trim()
        password = mViewDataBinding.userPass.getText().toString().trim()
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        initialization()

        if (!userEmail!!.isEmpty() || !password!!.isEmpty()) {

            val params = JsonObject()
            try {
                params.addProperty("email", userEmail)
                params.addProperty("password", password)
                params.addProperty("contact", userEmail)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            Log.e("UserData", params.toString())

            if (isValidEmail(userEmail.toString())) {
                mViewModel.login(params)
            } else {
                mViewModel.loginPhone(params)
            }

            mViewModel.loginResponse.observe(requireActivity()) {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->
//                            if (data.flag == 1) {
                            lifecycleScope.launch(Dispatchers.IO) {
                                dataStoreProvider.saveUserToken(data.token)
                                dataStoreProvider.saveUserDetails(
                                    data.user.name.toString(),
                                )
                            }
//                            sharedViewModel._profileData.value = it

                            navController = Navigation.findNavController(
                                requireActivity(),
                                R.id.nav_host_fragment
                            )
                            navController.navigate(R.id.homeFragment, null, options)
//                            } else {
//                                showToast(data.message)
//                            }
                        }
                    }
                    Resource.Status.ERROR -> {
                        loadingDialog.dismiss()
                        DialogHelperClass.errorDialog(requireContext(), it.message!!)
                    }
                }
            }
        }
    }

    fun isValidate(): Boolean {
        if (mViewDataBinding.userEmail.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_email))
            return false
        }

        if (mViewDataBinding.userPass.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_your_password))
            return false
        }
        if (mViewDataBinding.userPass.text.toString().trim().length < 8) {
            mViewDataBinding.root.snackbar(getString(R.string.password_8_character))
            return false
        }
        subscribeToNetworkLiveData()
        return true
    }



}