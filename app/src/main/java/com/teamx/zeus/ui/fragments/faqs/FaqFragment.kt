package com.teamx.zues.ui.fragments.PrivacyPolicyFragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.R
import com.teamx.zeus.BR
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.dataclasses.faq.FaqData
import com.teamx.zeus.databinding.FragmentFaqBinding
import com.teamx.zeus.databinding.FragmentPrivacyPolicyBinding
import com.teamx.zeus.dummyData.PaymentMethod
import com.teamx.zeus.ui.fragments.SignInFragment.AuthViewModel
import com.teamx.zeus.ui.fragments.faqs.FaqAdapter
import com.teamx.zeus.ui.fragments.paymentMethod.PaymentAdapter
import com.teamx.zeus.utils.PrefHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FaqFragment : BaseFragment<FragmentFaqBinding, AuthViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_faq
    override val viewModel: Class<AuthViewModel>
        get() = AuthViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var faqAdapter: FaqAdapter
    lateinit var faqArrayList: ArrayList<FaqData>



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewDataBinding.btnBack.setOnClickListener {
            popUpStack()
        }

        faqqAdapter()

    }

    private fun faqqAdapter(){
        faqArrayList = ArrayList()
        faqArrayList.add(FaqData("How to use pay on arrival ?","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam cursus massa at nisi aliquam venenatis. Curabitur efficitur augue a metus lacinia rhoncus eget a felis. Pellentesq"))


        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.faqRecycler.setLayoutManager(linearLayoutManager)

        faqAdapter = FaqAdapter(faqArrayList)
        mViewDataBinding.faqRecycler.adapter = faqAdapter

    }


}