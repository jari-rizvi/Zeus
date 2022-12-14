package com.teamx.zeus.ui.fragments.ContactUs

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.databinding.FragmentContactUsBinding
import com.teamx.zeus.databinding.FragmentForgotPassBinding
import com.teamx.zeus.ui.fragments.forgotPass.ForgotPassViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactUsFragment() : BaseFragment<FragmentContactUsBinding, ForgotPassViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_contact_us
    override val viewModel: Class<ForgotPassViewModel>
        get() = ForgotPassViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    private lateinit var options: NavOptions
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






    }


}