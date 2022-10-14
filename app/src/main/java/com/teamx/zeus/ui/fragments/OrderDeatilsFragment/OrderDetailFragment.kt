package com.teamx.zues.ui.fragments.OrderDeatilsFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.R
import com.teamx.zeus.BR
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.FragmentOrderDetailBinding
import com.teamx.zeus.utils.DialogHelperClass
import com.teamx.zues.dataclasses.allorders.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderDetailFragment : BaseFragment<FragmentOrderDetailBinding,
        OrderDetailViewModel>(),
    OnReviewListener {
    override val layoutId: Int
        get() = R.layout.fragment_order_detail
    override val viewModel: Class<OrderDetailViewModel>
        get() = OrderDetailViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var orderDetailAdapter: OrderDetailAdapter
    lateinit var orderDetailArrayList: ArrayList<Product>

    private var itemId: String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle = arguments
        if (bundle != null) {
            itemId = bundle.getString("itemId").toString()
            Log.d("ITEM_ID", itemId.toString())
        }

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }

        mViewModel.orderDetail(itemId.toString())

        mViewModel.orderDetailResponse.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->
//                        if (data.flag == 1) {
                        val shippingAddress = data.shipping_address
                        mViewDataBinding.orderId.text = "Id # " + data._id
                        mViewDataBinding.orderDate.text = data.createdAt.dropLast(14)

                        mViewDataBinding.txtdeliveryAddress.text =
                            "${shippingAddress.street_address}, " +
                                    /*  "${shippingAddress.city}, " +
                                      "${shippingAddress.state}, " +
                                      "${shippingAddress.country} \n" +*/
                                    "Zip code: ${shippingAddress.zip} "

                        mViewDataBinding.txtCityName.text = shippingAddress.city + ", "
                        mViewDataBinding.txtProvienceName.text = shippingAddress.state
                        mViewDataBinding.txtCountryName.text = shippingAddress.country
                        mViewDataBinding.totalprice.text = data.amount.toString() + " AED"

                        orderDetailArrayList.addAll(data.products)
                        orderDetailAdapter.notifyDataSetChanged()


//                        } else {
//                            showToast(data.message)
//                        }
                    }
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        })


        initializeAdapter()
    }

    private fun initializeAdapter() {

        orderDetailArrayList = ArrayList()
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.itemRecyclerview.setLayoutManager(linearLayoutManager)

        orderDetailAdapter = OrderDetailAdapter(orderDetailArrayList, this)
        mViewDataBinding.itemRecyclerview.adapter = orderDetailAdapter

    }


    override fun onReviewClickLister(position: Int) {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        navController.navigate(R.id.addReviewFragment, null)

    }
}