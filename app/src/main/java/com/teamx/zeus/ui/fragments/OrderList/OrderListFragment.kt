package com.teamx.zeus.ui.fragments.OrderList

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zues.dataclasses.allorders.DocX
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.*
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderListFragment() : BaseFragment<FragmentOrderListBinding, OrderListViewModel>(), OnOrderListListener {

    override val layoutId: Int
        get() = R.layout.fragment_order_list
    override val viewModel: Class<OrderListViewModel>
        get() = OrderListViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var orderListAdapter: OrderListAdapter
    lateinit var orderListArrayList: ArrayList<DocX>

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

        mViewModel.getOrderList(0, 10)

        mViewModel.orderListResponse.observe(requireActivity()) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->

                        orderListArrayList.clear()
                        orderListArrayList.addAll(data.docs)
                        orderListAdapter.notifyDataSetChanged()

                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        }

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }

        initializeAdapter()

    }

    private fun initializeAdapter() {

        orderListArrayList = ArrayList()

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.orderlistRecyclerView.layoutManager = linearLayoutManager

        orderListAdapter = OrderListAdapter(orderListArrayList,this)
        mViewDataBinding.orderlistRecyclerView.adapter = orderListAdapter

    }

    override fun OnOrderClickListener(position: String) {
    }

    override fun onAddReviewClickListener(position: String) {
        val bundle = Bundle()
        bundle.putString(
            "itemId", position
                .toString()
        ).toString()
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        navController.navigate(R.id.addReviewFragment, bundle, options)
    }

}