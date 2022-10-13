package com.teamx.zeus.ui.fragments.cart

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.viewpager2.widget.ViewPager2
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.databinding.FragmentViewpagerBinding
import com.teamx.zeus.ui.fragments.checkout.CheckoutFragment
import com.teamx.zeus.ui.fragments.paymentMethod.PaymentFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewpagerFragment() : BaseFragment<FragmentViewpagerBinding, CartViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_viewpager
    override val viewModel: Class<CartViewModel>
        get() = CartViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    lateinit var featureProductAdapter: ViewPagerAdapter
    lateinit var featureProductArrayList: ArrayList<Fragment>

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


        initializeFeatureProducts()

    }

    private fun initializeFeatureProducts() {

        featureProductArrayList = ArrayList()

        featureProductArrayList.add(CartFragment())
        featureProductArrayList.add(CheckoutFragment())
        featureProductArrayList.add(PaymentFragment())

        featureProductAdapter =
            ViewPagerAdapter(
                requireActivity().supportFragmentManager,
                requireActivity().lifecycle,
                featureProductArrayList
            )

        mViewDataBinding.viewPager.adapter = featureProductAdapter
        mViewDataBinding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL;
    }

}

