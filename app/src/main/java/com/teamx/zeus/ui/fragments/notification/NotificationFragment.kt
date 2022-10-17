package com.teamx.zeus.ui.fragments.notification

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.asLiveData
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamx.zues.dataclasses.notification.Doc
import com.teamx.zeus.R
import com.teamx.zeus.BR
import com.teamx.zeus.baseclasses.BaseFragment
import com.teamx.zeus.data.remote.Resource
import com.teamx.zeus.databinding.FragmentNotificationBinding
import com.teamx.zeus.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment() : BaseFragment<FragmentNotificationBinding, NotificationListViewModel>(),
    OnNotificationListener {

    override val layoutId: Int
        get() = R.layout.fragment_notification
    override val viewModel: Class<NotificationListViewModel>
        get() = NotificationListViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var options: NavOptions

    lateinit var notificationAdapter: NotificationAdapter
    lateinit var notificationArrayList: ArrayList<NotificationTableClass>
    lateinit var readNotificationArrayList: ArrayList<NotificationTableClass>
    lateinit var unReadNotificationArrayList: ArrayList<NotificationTableClass>
    data class NotificationTableClass(var id: Int = 0, var doc: Doc, var read: Boolean = false)


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

        dataStoreProvider.saveId.asLiveData().observe(viewLifecycleOwner) {
            it?.let {
                mViewModel.getNotification(it)
            }
        }

        mViewModel.getUnreadNotification(0, 10)
        mViewModel.getReadNotification()
        var str = ""
        dataStoreProvider.token.asLiveData().observe(requireActivity()) {
            str = it.toString()
        }
        Log.d("hello", "onViewCreated: $str")



        mViewModel.notificationListResponse.observe(requireActivity()) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    Log.d("1235", "onViewCreated: 22")
                    loadingDialog.dismiss()

                    it.data?.let {
                        readNotificationArrayList.clear()
                        for (a in it.docs) {
                            readNotificationArrayList.add(NotificationTableClass(0, a, true))
                        }
                    }
                    notificationArrayList.clear()
                    notificationArrayList.addAll(unReadNotificationArrayList)
                    notificationArrayList.addAll(readNotificationArrayList)
                    notificationAdapter.notifyDataSetChanged()
                }
                Resource.Status.ERROR -> {
                    Log.d("1235", "onViewCreated: rr")
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        }

        mViewModel.notificationReadListResponse.observe(requireActivity()) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
                    it.data?.let {
                        if (it.acknowledged) {
                            Log.d("TAG", "onViewCreated: ")
                        }
                    }

                }
                Resource.Status.ERROR -> {

                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        }

        mViewModel.notificationUnreadListResponse.observe(requireActivity()) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loadingDialog.show()
                }
                Resource.Status.SUCCESS -> {
//                    notificationArrayList.clear()
                    Log.d("1235", "onViewCreated: 22")
                    loadingDialog.dismiss()

                    it.data?.let {
                        unReadNotificationArrayList.clear()
                        for (a in it.docs) {
                            unReadNotificationArrayList.add(NotificationTableClass(0, a, false))
                        }
                    }
                    notificationArrayList.clear()
                    notificationArrayList.addAll(unReadNotificationArrayList)
                    notificationArrayList.addAll(readNotificationArrayList)
                    notificationAdapter.notifyDataSetChanged()

                }
                Resource.Status.ERROR -> {
                    Log.d("1235", "onViewCreated: rr")
                    loadingDialog.dismiss()
                    DialogHelperClass.errorDialog(requireContext(), it.message!!)
                }
            }
        }





        initalizeAdapter()

    }
    private fun initalizeAdapter() {

        notificationArrayList = ArrayList()
        readNotificationArrayList = ArrayList()
        unReadNotificationArrayList = ArrayList()

        notificationArrayList.addAll(unReadNotificationArrayList)
        notificationArrayList.addAll(readNotificationArrayList)
//        notificationArrayList.add(Notifications("Smiley’s Store marked your order #1982984 as shipped.","9:20 AM",R.drawable.icon_notification_dummy,"Smiley’s Store, #1982984"))

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.notificationRecyclerView.layoutManager = linearLayoutManager

        context?.let { notificationAdapter = NotificationAdapter(it, notificationArrayList, this) }
        mViewDataBinding.notificationRecyclerView.adapter = notificationAdapter
    }

    override fun OnNotificationClickListener(postion: Int) {
//        val snackBar = Snackbar.make(mViewDataBinding.notificationRecyclerView, "asdf", Snackbar.LENGTH_SHORT)
//        snackBar.show()

        val bundle = Bundle()
        bundle.putString(
            "itemId", notificationArrayList[postion].doc.order_id
                .toString()
        ).toString()
//        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
//        navController.navigate(R.id.orderDetailFragment, bundle, options)
    }



}