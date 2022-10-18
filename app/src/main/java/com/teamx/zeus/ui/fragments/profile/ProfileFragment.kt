package com.teamx.zeus.ui.fragments.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.squareup.picasso.Picasso
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.databinding.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment() : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_profile
    override val viewModel: Class<ProfileViewModel>
        get() = ProfileViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions


    fun header(){
        val username = mViewDataBinding.textView47
        val profilePicture = mViewDataBinding.profilePicture
        val userEmail = mViewDataBinding.tvEmail



        var details = ""
        dataStoreProvider.details.asLiveData().observe(requireActivity()) {
            val details = it
            userEmail.text = details.toString()
            Log.d("USERNAME", details.toString())
        }
        if (details.isEmpty()) {
            dataStoreProvider.details.asLiveData().observe(requireActivity()) {
                details = it ?: ""
                userEmail.text = details
                Log.d("USERNAME", details)
            }
        }

        dataStoreProvider.avatar.asLiveData().observe(requireActivity()) {
            Picasso.get().load(it).into(profilePicture)
        }

        dataStoreProvider.name.asLiveData().observe(requireActivity()) {
            username.text = it
        }


        profilePicture.setOnClickListener {
            navController = Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
            )
            navController.navigate(R.id.editProfileFragment, null, null)
        }

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

        header()

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }

        mViewDataBinding.tvProfile

        mViewDataBinding.btnCoupons.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.couponFragment, null,options)
        }

        mViewDataBinding.btnAddress.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.addressFragment, null,options)
        }

        mViewDataBinding.btnOrderHistory.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.orderHistoryFragment, null,options)
        }

        mViewDataBinding.btnPaymentMethod.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.paymentFragment, null,options)
        }

        mViewDataBinding.btnSettings.setOnClickListener {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.settingsFragment, null,options)
        }

        mViewDataBinding.btnLogout.setOnClickListener {

            if(isAdded){
                mViewModel.logOutUser()
                lifecycleScope.launch(Dispatchers.IO) {
                    dataStoreProvider.removeAll()

                }

                navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                navController.popBackStack(R.id.homeFragment, true);
                navController.navigate(R.id.signInFragment, null, null)


            }
        }


    }

}