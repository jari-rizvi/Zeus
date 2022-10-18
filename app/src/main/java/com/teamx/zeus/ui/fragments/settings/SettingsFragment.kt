package com.teamx.zeus.ui.fragments.settings

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.databinding.FragmentSettingsBinding
import com.teamx.zeus.databinding.FragmentTempBinding
import com.teamx.zeus.ui.fragments.temp.TempViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding, TempViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_settings
    override val viewModel: Class<TempViewModel>
        get() = TempViewModel::class.java
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

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()

        }

    }

}