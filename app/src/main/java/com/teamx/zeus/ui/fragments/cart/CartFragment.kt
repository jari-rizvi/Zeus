package com.teamx.zeus.ui.fragments.cart

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.FragmentCartBinding
import com.teamx.zeus.dummyData.Cart
import com.teamx.zeus.utils.DialogHelperClass
import com.teamx.zeus.data.local.dbModel.CartTable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment() : BaseFragment<FragmentCartBinding, CartViewModel>(),
    OnCartListener {

    override val layoutId: Int
        get() = R.layout.fragment_cart
    override val viewModel: Class<CartViewModel>
        get() = CartViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    var cartAdapter: CartAdapter? = null
    var cartArrayList: MutableLiveData<ArrayList<Cart>>? = null
    var cartArrayList2: ArrayList<Cart>? = null
    var cartTableArrayList: ArrayList<CartTable>? = null

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


        cartArrayList?.observe(viewLifecycleOwner) {
            var totalPrice = 0.0
            for (cart in it) {
                cart.productBySlug?.let { itt ->
                    if (itt.product_type.toString().equals("simple")) {
                        totalPrice += itt.price!! * cart.quantity
//                        mViewDataBinding.productPrice.text = data.price.toString()
                    } else {
                        totalPrice += itt.price!! * cart.quantity
//                        mViewDataBinding.productPrice.text = "${data.min_price.toString()}-${data.max_price.toString()}"
                    }
                }
            }
            mViewDataBinding.subTotal.text = "$totalPrice"

        }



        loadCart()
        initializeAdapter()
        mViewModel.getCarts()

    }

    private fun loadCart() {
        cartArrayList?.value = cartArrayList2
        mViewModel.cartResponse.observe(requireActivity()) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismiss()
                    it.data?.let { data ->
                        cartArrayList2?.clear()
                        Log.d("TAG", "LoadCart: $data")
                        for (ii in data) {
                            Log.d("TAG", "LoadCart: $ii")
                            cartArrayList2?.add(
                                Cart(
                                    ii.id,
                                    ii.product.name ?: "",
                                    ii.product.product_type ?: "",
                                    ii.product.price?.toDouble() ?: 0.0,
                                    ii.product.image ?: "", productBySlug = ii.product
                                )
                            )
                        }
                        cartArrayList?.value = cartArrayList2

                        cartArrayList2?.let { it1 -> cartAdapter?.setData(it1) }

                    }
//                    mViewModel._cartTableResponse2.value = it
                }
                Resource.Status.ERROR -> {
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        }


    }


    private fun initializeAdapter() {
        cartTableArrayList = ArrayList()
        cartArrayList2 = ArrayList()
        cartArrayList = MutableLiveData(ArrayList())


        cartArrayList?.value = cartArrayList2

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.cartRecyclerview.layoutManager = linearLayoutManager
        cartAdapter = CartAdapter(cartArrayList?.value!!, this)
        mViewDataBinding.cartRecyclerview.adapter = cartAdapter
        cartArrayList?.observe(viewLifecycleOwner) {
            var totalPrice = 0.0
            for (cart in it) {
                cart.productBySlug?.let { itt ->
                    if (itt.product_type.toString().equals("simple")) {
                        totalPrice += itt.price!! * cart.quantity
//                        mViewDataBinding.productPrice.text = data.price.toString()
                    } else {
//                        totalPrice += itt.price!! * cart.quantity
//                        mViewDataBinding.productPrice.text = "${data.min_price.toString()}-${data.max_price.toString()}"
                    }
                }
            }
            mViewDataBinding.subTotal.text = "$totalPrice"

        }

    }

    override fun onAddClickListener(position: Int) {
        cartArrayList2!![position].quantity = cartArrayList2!![position].quantity + 1
        cartArrayList?.value = cartArrayList2


    }

    override fun onSubClickListener(position: Int) {

        if (cartArrayList2!![position].quantity > 1) {
            cartArrayList2!![position].quantity = cartArrayList2!![position].quantity - 1
        }
        cartArrayList?.value = cartArrayList2
    }


}