package com.teamx.zeus.ui.fragments.ReviewList

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
import com.teamx.zeus.SharedViewModel
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
class ReviewListFragment() : BaseFragment<FragmentReviewListBinding, ReviewListViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_review_list
    override val viewModel: Class<ReviewListViewModel>
        get() = ReviewListViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var reviewListAdapter: ReviewListAdapter
    lateinit var reviewListArrayList: ArrayList<Doc>

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

        initializeAdapter();


        Log.d("jsdks",sharedViewModel.productBySlug.value!!)
        mViewModel.getReviewList(sharedViewModel.productBySlug.value!!, 0, 10)

        mViewModel.reviewListResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    Log.d("1235", "onViewCreated: success")
                    loadingDialog.dismiss()
                    it.data?.let { data ->
//                        if (data.flag == 1) {
                        reviewListArrayList.clear()

                        reviewListArrayList.addAll(data.docs)
                        reviewListAdapter.notifyDataSetChanged()

//                        } else {

//                        }
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
        reviewListArrayList = ArrayList()
//        orderListArrayList.add(OrderList("Id #123456789", "49.99 AED", "Monday 21 April"))
//        orderListArrayList.add(OrderList("Id #123456789", "49.99 AED", "Monday 21 April"))
//        orderListArrayList.add(OrderList("Id #123456789", "49.99 AED", "Monday 21 April"))

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.reviewRecyclerView.setLayoutManager(linearLayoutManager)

        reviewListAdapter = ReviewListAdapter(reviewListArrayList)
        mViewDataBinding.reviewRecyclerView.adapter = reviewListAdapter

    }


}