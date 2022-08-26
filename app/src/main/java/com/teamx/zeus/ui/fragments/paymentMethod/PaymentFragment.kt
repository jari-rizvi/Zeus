package com.teamx.zeus.ui.fragments.paymentMethod

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.databinding.FragmentPaymentMethodBinding
import com.teamx.zeus.dummyData.PaymentMethod
import com.teamx.zeus.ui.fragments.Home.OnTopSellerListener
import com.teamx.zeus.ui.fragments.SignInFragment.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentMethodBinding, AuthViewModel>(),
    OnTopSellerListener {
    override val layoutId: Int
        get() = R.layout.fragment_payment_method
    override val viewModel: Class<AuthViewModel>
        get() = AuthViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var recomendedAdapter: PaymentAdapter
    lateinit var recomendedArrayList: ArrayList<PaymentMethod>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()

        }


        recomendedViewAdapter();

    }

    private fun recomendedViewAdapter(){
        recomendedArrayList = ArrayList()
        recomendedArrayList.add(PaymentMethod(1,R.drawable.icon_master,getString(R.string.debit_card)))
        recomendedArrayList.add(PaymentMethod(3,R.drawable.icon_cash,"Cash"))

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.paymentMethodRecyclerview.setLayoutManager(linearLayoutManager)

        recomendedAdapter = PaymentAdapter(recomendedArrayList,this)
        mViewDataBinding.paymentMethodRecyclerview.adapter = recomendedAdapter
    }

    override fun onTopSellerClick(position: Int) {
        showToast(""+ recomendedArrayList[position].paymentName)

    }


}