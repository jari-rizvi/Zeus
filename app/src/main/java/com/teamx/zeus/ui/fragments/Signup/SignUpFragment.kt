package com.teamx.zeus.ui.fragments.Signup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStarted
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
import com.teamx.zeus.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_up.*
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
            isValidate()
        }

        mViewDataBinding.btnPrivacyPolicy.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data =
                Uri.parse("https://multivendor-front-bonik.vercel.app/terms-and-conditions")
            startActivity(openURL)
        }

        mViewDataBinding.btnTermsnCondition.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data =
                Uri.parse("https://multivendor-front-bonik.vercel.app/terms-and-conditions")
            startActivity(openURL)
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

        if (!userNumber!!.isEmpty() || !password!!.isEmpty() || name!!.isNotEmpty()) {

            val params = JsonObject()
            try {
                params.addProperty("name", name.toString())
                params.addProperty("contact", userNumber.toString())
                params.addProperty("password", password.toString())
                params.addProperty("permission", "customer")
                Log.e("UserData", params.toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }

           mViewModel.signup(params)

            mViewModel.signupResponse.observe(requireActivity(), Observer {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }
                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->

                            val bundle = Bundle()
                            bundle.putString("phone",data.phone_number)
                            bundle.putString("Sid",data.twilio.sid)
                            bundle.putString("otpid",data.id)


                            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                            navController.navigate(R.id.otpFragment, bundle,options)
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

    fun isValidate(): Boolean {

        if (mViewDataBinding.userName.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_name))
            return false
        }
        if (mViewDataBinding.userName.text.toString().trim().length < 3) {
            mViewDataBinding.root.snackbar(getString(R.string.name_must_have_atleast_3_character_long))
            return false
        }

        if (mViewDataBinding.userName.text.toString().trim().length > 15) {
            mViewDataBinding.root.snackbar(getString(R.string.name_maximum))
            return false
        }
        if (mViewDataBinding.userEmail.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_email))
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mViewDataBinding.userEmail.text.toString().trim()).matches()){
            mViewDataBinding.root.snackbar(getString(R.string.invalid_email))
            return  false
        }
        if (mViewDataBinding.userPhone.text.toString().trim().isEmpty()) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_your_password))
            return false
        }
        if (mViewDataBinding.userPhone.text.toString().trim().startsWith("+")) {
            mViewDataBinding.root.snackbar(getString(R.string.enter_Number_with_Country_Code))
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