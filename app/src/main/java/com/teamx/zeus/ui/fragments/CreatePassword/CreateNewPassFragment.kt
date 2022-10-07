package com.teamx.zeus.ui.fragments.CreatePassword

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.google.gson.JsonObject
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.FragmentCreatePasswordBinding
import com.teamx.zeus.databinding.FragmentForgotPassBinding
import com.teamx.zeus.utils.DialogHelperClass
import com.teamx.zeus.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException

@AndroidEntryPoint
class CreateNewPassFragment() :
    BaseFragment<FragmentCreatePasswordBinding, CreateNewPassViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_create_password
    override val viewModel: Class<CreateNewPassViewModel>
        get() = CreateNewPassViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private var newPass: String? = null
    private var email: String? = null
    private var token: String? = null

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

        mViewDataBinding.btnNext.setOnClickListener{
        validate()

        }
    }

        private fun resetPassCall() {
            super.subscribeToNetworkLiveData()

            val bundle = arguments
            if (bundle != null) {
                newPass = mViewDataBinding.newPass.text.toString()
                token = bundle.getString("token").toString()
                email = bundle.getString("email").toString()
            }

            val params = JsonObject()
            try {
                params.addProperty("password", newPass)
                params.addProperty("token", token)
                params.addProperty("email", email)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            mViewModel.resetPass(params)

            mViewModel.resetPassResponse.observe(requireActivity(), Observer {
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
                            navController.navigate(R.id.homeFragment, null, options)
                        }
                    }
                    Resource.Status.ERROR -> {
                        loadingDialog.dismiss()
                        DialogHelperClass.errorDialog(requireContext(), it.message!!)
                    }
                }
            })
        }

    fun validate(): Boolean {
        if (mViewDataBinding.newPass.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_Password))
            return false
        }
        if (mViewDataBinding.newPass.text.toString().trim().length < 8) {
            mViewDataBinding.root.snackbar(getString(R.string.password_8_character))
            return false
        }
        if (mViewDataBinding.confirmPass.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_Password))
            return false
        }
        if (mViewDataBinding.confirmPass.text.toString().trim().length < 7) {
            mViewDataBinding.root.snackbar(getString(R.string.password_8_character))
            return false
        }
        if (!mViewDataBinding.newPass.text.toString().trim().equals(mViewDataBinding.confirmPass.text.toString().trim())
        ) {
            mViewDataBinding.root.snackbar(getString(R.string.password_does_not_match))
            return false
        }
        resetPassCall()
        return true
    }

}