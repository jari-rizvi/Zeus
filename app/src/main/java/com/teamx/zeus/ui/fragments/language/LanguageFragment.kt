package com.teamx.zeus.ui.fragments.language

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.gson.JsonObject
import com.teamx.zeus.BR
import com.teamx.zeus.MainApplication
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.*
import com.teamx.zeus.localization.LocaleManager
import com.teamx.zeus.ui.fragments.otp.OtpViewModel
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException


@AndroidEntryPoint
class LanguageFragment() : BaseFragment<FragmentLanguageBinding, OtpViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_language
    override val viewModel: Class<OtpViewModel>
        get() = OtpViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

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

    }




}