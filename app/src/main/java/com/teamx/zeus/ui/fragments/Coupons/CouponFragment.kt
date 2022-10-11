package com.teamx.zeus.ui.fragments.Coupons

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
import com.teamx.multivendor.dataclasses.allreviews.Doc
import com.teamx.zeus.BR
import com.teamx.zeus.MainApplication
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.dataclasses.coupouns.CoupounData
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.*
import com.teamx.zeus.localization.LocaleManager
import com.teamx.zeus.ui.fragments.ReviewList.ReviewListAdapter
import com.teamx.zeus.ui.fragments.SignInFragment.AuthViewModel
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException


@AndroidEntryPoint
class CouponFragment() : BaseFragment<FragmentCouponsBinding, CoupounViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_coupons
    override val viewModel: Class<CoupounViewModel>
        get() = CoupounViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    private lateinit var options: NavOptions
    lateinit var coupounsListAdapter: CoupounListAdapter
    lateinit var coupounsListArrayList: ArrayList<com.teamx.zeus.data.dataclasses.coupouns.Doc>

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

        initializeAdapter()

        mViewModel.getCoupoun()

        mViewModel.coupounResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {

                    Log.d("1235", "onViewCreated: success")

                    loadingDialog.dismiss()
                    it.data?.let { data ->
                        coupounsListArrayList.clear()
                        coupounsListArrayList.addAll(data.docs)
                        coupounsListAdapter.notifyDataSetChanged()

                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        })

    }

    private fun initializeAdapter() {
        coupounsListArrayList = ArrayList()

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.couponRecyclerview.setLayoutManager(linearLayoutManager)

        coupounsListAdapter = CoupounListAdapter(coupounsListArrayList)
        mViewDataBinding.couponRecyclerview.adapter = coupounsListAdapter

    }

}