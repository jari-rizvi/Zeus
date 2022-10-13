package com.teamx.zeus.ui.fragments.paymentMethod

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.databinding.FragmentPaymentMethodBinding
import com.teamx.zeus.dummyData.PaymentMethod
import com.teamx.zeus.ui.fragments.Home.OnTopSellerListener
import com.teamx.zeus.ui.fragments.SignInFragment.AuthViewModel
import com.teamx.zeus.utils.PrefHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentMethodBinding, AuthViewModel>(),
    OnTopSellerListener {
    override val layoutId: Int
        get() = R.layout.fragment_payment_method
    override val viewModel: Class<AuthViewModel>
        get() = AuthViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var paymentAdapter: PaymentAdapter
    lateinit var paymentArrayList: ArrayList<PaymentMethod>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        paymentAdapter();

    }

    private fun paymentAdapter(){
        paymentArrayList = ArrayList()
        if(PrefHelper.getInstance(requireContext()).payment.equals(1)){
            paymentArrayList.add(PaymentMethod(1,R.drawable.icon_master,getString(R.string.debit_card)))
            paymentArrayList.add(PaymentMethod(paymentId =  2,R.drawable.icon_cash,"Cash",true))
        }
        else{
            paymentArrayList.add(PaymentMethod(1,R.drawable.icon_master,getString(R.string.debit_card),true))
            paymentArrayList.add(PaymentMethod(2,R.drawable.icon_cash,"Cash"))
        }

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.paymentMethodRecyclerview.setLayoutManager(linearLayoutManager)

        paymentAdapter = PaymentAdapter(paymentArrayList,this)
        mViewDataBinding.paymentMethodRecyclerview.adapter = paymentAdapter

    }

    override fun onTopSellerClick(position: Int) {

        PrefHelper.getInstance(requireContext()).savePayment(position)

        for(cat in paymentArrayList){
            cat.value = false }
        paymentArrayList.get(position).value = true
        paymentAdapter.notifyDataSetChanged()



//
//        lifecycleScope.launch(Dispatchers.IO) {
//        dataStoreProvider.savePaymentMethod(paymentArrayList[position].paymentId)
//        }
//
//        dataStoreProvider.payment.asLiveData().observe(
//            requireActivity()
//        ) {
//            val payment = it
//            Log.e("valjueeeof", payment.toString())
//            if (payment != null) {
//
//                paymentAdapter.row_index = 1
//
//            } else {
//
//            }
//        }


//        showToast(""+ recomendedArrayList[position].paymentName)

    }


}