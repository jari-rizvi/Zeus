package com.teamx.zeus.ui.fragments.currency

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.multivendor.dataclasses.allorders.DocX
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.dataclasses.currency.CurrencyData
import com.teamx.zeus.data.dataclasses.currency.CurrencyModel
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.*
import com.teamx.zeus.ui.fragments.OrderList.OrderListAdapter
import com.teamx.zeus.ui.fragments.OrderList.OrderListViewModel
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment() : BaseFragment<FragmentCurrencyBinding, CurrencyViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_currency
    override val viewModel: Class<CurrencyViewModel>
        get() = CurrencyViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var currencyListAdapter: CurrencyListAdapter
    lateinit var currencyListArrayList: ArrayList<CurrencyModel>

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

//        mViewModel.getCurrencyList()
//
//        mViewModel.currencyListResponse.observe(requireActivity()) {
//            when (it.status) {
//                Resource.Status.LOADING -> {
//                    loadingDialog.show()
//                }
//                Resource.Status.SUCCESS -> {
//                    loadingDialog.dismiss()
//                    it.data?.let { data ->
//
//                        currencyListArrayList.clear()
//                        currencyListArrayList.addAll(data.rates)
//                        currencyListAdapter.notifyDataSetChanged()
//
//                    }
//                }
//                Resource.Status.ERROR -> {
//                    loadingDialog.dismiss()
//                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
//                }
//            }
//        }


        initializeAdapter()

    }

    private fun initializeAdapter() {

        currencyListArrayList = ArrayList()
        currencyListArrayList.add(CurrencyModel("AED", 1.0))
        currencyListArrayList.add(CurrencyModel("AFN", 23.949823))
        currencyListArrayList.add(CurrencyModel("ALL", 32.263106))
        currencyListArrayList.add(CurrencyModel("AMD", 110.360165))
        currencyListArrayList.add(CurrencyModel("ANG", 0.487406))
        currencyListArrayList.add(CurrencyModel("AOA", 118.806929))
        currencyListArrayList.add(CurrencyModel("ARS", 40.552152))
        currencyListArrayList.add(CurrencyModel("AUD", 0.423405))
        currencyListArrayList.add(CurrencyModel("AWG", 0.487406))
        currencyListArrayList.add(CurrencyModel("AZN", 0.46255))
        currencyListArrayList.add(CurrencyModel("BAM", 0.542188))
        currencyListArrayList.add(CurrencyModel("BBD", 0.544588))
        currencyListArrayList.add(CurrencyModel("BDT", 27.806027))
        currencyListArrayList.add(CurrencyModel("BGN", 0.542188))
        currencyListArrayList.add(CurrencyModel("BHD", 0.102383))
        currencyListArrayList.add(CurrencyModel("BIF", 557.402738))
        currencyListArrayList.add(CurrencyModel("BMD", 0.272294))
        currencyListArrayList.add(CurrencyModel("BND", 0.38876))
        currencyListArrayList.add(CurrencyModel("BOB", 1.888596))
        currencyListArrayList.add(CurrencyModel("BRL", 1.415084))
        currencyListArrayList.add(CurrencyModel("BSD", 0.272294))
        currencyListArrayList.add(CurrencyModel("BTN", 22.326723))
        currencyListArrayList.add(CurrencyModel("BWP", 3.620587))
        currencyListArrayList.add(CurrencyModel("BYN", 0.739382))
        currencyListArrayList.add(CurrencyModel("BZD", 0.544588))
        currencyListArrayList.add(CurrencyModel("CAD", 0.37332))
        currencyListArrayList.add(CurrencyModel("CDF", 552.184825))
        currencyListArrayList.add(CurrencyModel("CHF", 0.269193))
        currencyListArrayList.add(CurrencyModel("CLP", 256.143789))
        currencyListArrayList.add(CurrencyModel("CNY", 1.931079))
        currencyListArrayList.add(CurrencyModel("COP", 1237.296371))
        currencyListArrayList.add(CurrencyModel("CRC", 171.10658))
        currencyListArrayList.add(CurrencyModel("CUP", 6.535058))
        currencyListArrayList.add(CurrencyModel("CVE", 30.567254))
        currencyListArrayList.add(CurrencyModel("CZK", 6.797273))
        currencyListArrayList.add(CurrencyModel("DJF", 48.392376))
        currencyListArrayList.add(CurrencyModel("DKK", 2.068139))
        currencyListArrayList.add(CurrencyModel("DOP", 14.455354))
        currencyListArrayList.add(CurrencyModel("DZD", 38.281502))
        currencyListArrayList.add(CurrencyModel("EGP", 5.354421))

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.currencyRecyclerView.layoutManager = linearLayoutManager

        currencyListAdapter = CurrencyListAdapter(currencyListArrayList)
        mViewDataBinding.currencyRecyclerView.adapter = currencyListAdapter

    }

}