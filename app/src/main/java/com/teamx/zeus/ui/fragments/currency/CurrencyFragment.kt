package com.teamx.zeus.ui.fragments.currency

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zues.dataclasses.allorders.DocX
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.dataclasses.currency.CurrencyData
import com.teamx.zeus.data.dataclasses.currency.CurrencyModel
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.*
import com.teamx.zeus.ui.fragments.Home.OnTopCategoriesListener
import com.teamx.zeus.ui.fragments.OrderList.OrderListAdapter
import com.teamx.zeus.ui.fragments.OrderList.OrderListViewModel
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment() : BaseFragment<FragmentCurrencyBinding, CurrencyViewModel>(),
    OnCurrencyListener {

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

        initializeAdapter()

    }

    private fun initializeAdapter() {

        currencyListArrayList = ArrayList()
        currencyListArrayList.add(CurrencyModel("AED", 1.0,true))
        currencyListArrayList.add(CurrencyModel("AFN", 23.949823,false))
        currencyListArrayList.add(CurrencyModel("ALL", 32.263106,false))
        currencyListArrayList.add(CurrencyModel("AMD", 110.360165,false))
        currencyListArrayList.add(CurrencyModel("ANG", 0.487406,false))
        currencyListArrayList.add(CurrencyModel("AOA", 118.806929,false))
        currencyListArrayList.add(CurrencyModel("ARS", 40.552152,false))
        currencyListArrayList.add(CurrencyModel("AUD", 0.423405,false))
        currencyListArrayList.add(CurrencyModel("AWG", 0.487406,false))
        currencyListArrayList.add(CurrencyModel("AZN", 0.46255,false))
        currencyListArrayList.add(CurrencyModel("BAM", 0.542188,false))
        currencyListArrayList.add(CurrencyModel("BBD", 0.544588,false))
        currencyListArrayList.add(CurrencyModel("BDT", 27.806027,false))
        currencyListArrayList.add(CurrencyModel("BGN", 0.542188,false))
        currencyListArrayList.add(CurrencyModel("BHD", 0.102383,false))
        currencyListArrayList.add(CurrencyModel("BIF", 557.402738,false))
        currencyListArrayList.add(CurrencyModel("BMD", 0.272294,false))
        currencyListArrayList.add(CurrencyModel("BND", 0.38876,false))
        currencyListArrayList.add(CurrencyModel("BOB", 1.888596,false))
        currencyListArrayList.add(CurrencyModel("BRL", 1.415084,false))
        currencyListArrayList.add(CurrencyModel("BSD", 0.272294,false))
        currencyListArrayList.add(CurrencyModel("BTN", 22.326723,false))
        currencyListArrayList.add(CurrencyModel("BWP", 3.620587,false))
        currencyListArrayList.add(CurrencyModel("BYN", 0.739382,false))
        currencyListArrayList.add(CurrencyModel("BZD", 0.544588,false))
        currencyListArrayList.add(CurrencyModel("CAD", 0.37332,false))
        currencyListArrayList.add(CurrencyModel("CDF", 552.184825,false))
        currencyListArrayList.add(CurrencyModel("CHF", 0.269193,false))
        currencyListArrayList.add(CurrencyModel("CLP", 256.143789,false))
        currencyListArrayList.add(CurrencyModel("CNY", 1.931079,false))
        currencyListArrayList.add(CurrencyModel("COP", 1237.296371,false))
        currencyListArrayList.add(CurrencyModel("CRC", 171.10658,false))
        currencyListArrayList.add(CurrencyModel("CUP", 6.535058,false))
        currencyListArrayList.add(CurrencyModel("CVE", 30.567254,false))
        currencyListArrayList.add(CurrencyModel("CZK", 6.797273,false))
        currencyListArrayList.add(CurrencyModel("DJF", 48.392376,false))
        currencyListArrayList.add(CurrencyModel("DKK", 2.068139,false))
        currencyListArrayList.add(CurrencyModel("DOP", 14.455354,false))
        currencyListArrayList.add(CurrencyModel("DZD", 38.281502,false))
        currencyListArrayList.add(CurrencyModel("EGP", 5.354421,false))

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.currencyRecyclerView.layoutManager = linearLayoutManager

        currencyListAdapter = CurrencyListAdapter(currencyListArrayList,this)
        mViewDataBinding.currencyRecyclerView.adapter = currencyListAdapter

    }


    override fun onCurrencyClick(position: Int) {
        for(cat in currencyListArrayList){
            cat.isChecked = false
        }
        currencyListArrayList.get(position).isChecked = true
        currencyListAdapter.notifyDataSetChanged()

    }

}