package com.teamx.zeus.ui.fragments.CreatePassword

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.databinding.FragmentCreatePasswordBinding
import com.teamx.zeus.databinding.FragmentForgotPassBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewPassFragment() : BaseFragment<FragmentCreatePasswordBinding, CreateNewPassViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_create_password
    override val viewModel: Class<CreateNewPassViewModel>
        get() = CreateNewPassViewModel::class.java
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