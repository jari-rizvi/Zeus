package com.teamx.zeus.ui.fragments.redeemPoints

import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zeus.BR
import com.teamx.zeus.R
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.databinding.FragmentNotificationBinding
import com.teamx.zeus.ui.fragments.notification.NotificationAdapter
import com.teamx.zeus.ui.fragments.notification.Notifications
import com.teamx.zeus.ui.fragments.otp.OtpViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RedeemPointsFragment() : BaseFragment<FragmentNotificationBinding, OtpViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_notification
    override val viewModel: Class<OtpViewModel>
        get() = OtpViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions
    lateinit var notificationAdapter: NotificationAdapter
    lateinit var notificationArrayList: ArrayList<Notifications>


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

//        initalizeAdapter()

    }
//    private fun initalizeAdapter() {
//
//        notificationArrayList = ArrayList()
//        notificationArrayList.add(Notifications("Your Order has been Placed successfully.","2 days ago",R.drawable.rate_us,"Smiley’s Store, #1982984"))
//        notificationArrayList.add(Notifications("Your Order has been Placed successfully.","2 days ago",R.drawable.rate_us,"Smiley’s Store, #1982984"))
//        notificationArrayList.add(Notifications("Your Order has been Placed successfully.","2 days ago",R.drawable.rate_us,"Smiley’s Store, #1982984"))
//        notificationArrayList.add(Notifications("Your Order has been Placed successfully.","2 days ago",R.drawable.rate_us,"Smiley’s Store, #1982984"))
//
//
//        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//        mViewDataBinding.notificationRecyclerView.setLayoutManager(linearLayoutManager)
//
//        notificationAdapter = NotificationAdapter(context,notificationArrayList)
//        mViewDataBinding.notificationRecyclerView.adapter = notificationAdapter
//    }




}