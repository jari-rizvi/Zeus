package com.teamx.zeus.ui.fragments.addReview

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.databinding.FragmentAddReviewBinding
import com.teamx.zeus.databinding.FragmentOrderListBinding
import com.teamx.zeus.ui.fragments.otp.OtpViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddReviewFragment() : BaseFragment<FragmentAddReviewBinding, OtpViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_add_review
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